import { memo, useCallback, useMemo, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { movePost } from "../features/posts/postsSlice";

const weekDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const getDateKey = (date) => {
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const day = String(date.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

function CalendarView({ onAddPost, onSelectPost }) {
  const dispatch = useDispatch();
  const posts = useSelector((state) => state.posts.posts);

  const [currentMonth, setCurrentMonth] = useState(new Date());

  // Creates the 42 cells needed for a complete monthly calendar view.
  const calendarDays = useMemo(() => {
    const year = currentMonth.getFullYear();
    const month = currentMonth.getMonth();

    const firstDay = new Date(year, month, 1);
    const startingDay = firstDay.getDay();

    const firstCellDate = new Date(year, month, 1 - startingDay);

    return Array.from({ length: 42 }, (_, index) => {
      const date = new Date(firstCellDate);
      date.setDate(firstCellDate.getDate() + index);

      return date;
    });
  }, [currentMonth]);

  // Groups posts once, instead of filtering all posts for every cell.
  const postsByDate = useMemo(() => {
    return posts.reduce((groupedPosts, post) => {
      if (!groupedPosts[post.date]) {
        groupedPosts[post.date] = [];
      }

      groupedPosts[post.date].push(post);
      return groupedPosts;
    }, {});
  }, [posts]);

  const changeMonth = useCallback((amount) => {
    setCurrentMonth((month) => {
      return new Date(month.getFullYear(), month.getMonth() + amount, 1);
    });
  }, []);

  const handleDragStart = useCallback((event, postId) => {
    event.dataTransfer.setData("postId", postId.toString());
  }, []);

  const handleDrop = useCallback(
    (event, newDate) => {
      event.preventDefault();

      const postId = Number(event.dataTransfer.getData("postId"));

      if (postId) {
        dispatch(
          movePost({
            id: postId,
            newDate: getDateKey(newDate),
          })
        );
      }
    },
    [dispatch]
  );

  const monthTitle = currentMonth.toLocaleDateString("en-US", {
    month: "long",
    year: "numeric",
  });

  return (
    <section className="calendar-section">
      <div className="calendar-header">
        <button onClick={() => changeMonth(-1)}>← Previous</button>
        <h2>{monthTitle}</h2>
        <button onClick={() => changeMonth(1)}>Next →</button>
      </div>

      <div className="weekdays">
        {weekDays.map((day) => (
          <div key={day}>{day}</div>
        ))}
      </div>

      <div className="calendar-grid">
        {calendarDays.map((date) => {
          const dateKey = getDateKey(date);
          const isCurrentMonth =
            date.getMonth() === currentMonth.getMonth();

          const postsForDay = postsByDate[dateKey] || [];

          return (
            <div
              className={`calendar-day ${
                isCurrentMonth ? "" : "other-month"
              }`}
              key={dateKey}
              onClick={() => onAddPost(dateKey)}
              onDragOver={(event) => event.preventDefault()}
              onDrop={(event) => handleDrop(event, date)}
            >
              <span className="day-number">{date.getDate()}</span>

              {postsForDay
                .slice()
                .sort((a, b) => a.time.localeCompare(b.time))
                .map((post) => (
                  <button
                    className="calendar-post"
                    draggable
                    key={post.id}
                    onDragStart={(event) =>
                      handleDragStart(event, post.id)
                    }
                    onClick={(event) => {
                      event.stopPropagation();
                      onSelectPost(post);
                    }}
                  >
                    <strong>{post.time}</strong> {post.title}
                  </button>
                ))}
            </div>
          );
        })}
      </div>
    </section>
  );
}

export default memo(CalendarView);