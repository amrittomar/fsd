import { useCallback, useMemo, useState } from "react";
import { useSelector } from "react-redux";
import "./App.css";

import AddPostModal from "./components/AddPostModal";
import CalendarView from "./components/CalendarView";
import EventDetails from "./components/EventDetails";
import Navbar from "./components/Navbar";

const getTodayDate = () => {
  const today = new Date();

  const year = today.getFullYear();
  const month = String(today.getMonth() + 1).padStart(2, "0");
  const day = String(today.getDate()).padStart(2, "0");

  return `${year}-${month}-${day}`;
};

function App() {
  const posts = useSelector((state) => state.posts.posts);

  const [selectedDate, setSelectedDate] = useState(null);
  const [selectedPost, setSelectedPost] = useState(null);

  // useMemo prevents recalculating the count on unrelated UI changes.
  const totalPosts = useMemo(() => posts.length, [posts]);

  // useCallback gives CalendarView and Navbar stable function references.
  const openAddPostModal = useCallback((date) => {
    setSelectedPost(null);
    setSelectedDate(date);
  }, []);

  const openEditPostModal = useCallback((post) => {
    setSelectedDate(null);
    setSelectedPost(post);
  }, []);

  const closeModals = useCallback(() => {
    setSelectedDate(null);
    setSelectedPost(null);
  }, []);

  return (
    <div className="app">
      <Navbar onSchedulePost={() => openAddPostModal(getTodayDate())} />

      <main className="main-content">
        <section className="summary-card">
          <div>
            <h2>Content Calendar</h2>
            <p>
              Click a date to schedule a post. Drag a post to another date to
              reschedule it.
            </p>
          </div>

          <div className="post-count">
            <strong>{totalPosts}</strong>
            <span>Scheduled Posts</span>
          </div>
        </section>

        <CalendarView
          onAddPost={openAddPostModal}
          onSelectPost={openEditPostModal}
        />
      </main>

      {selectedDate && (
        <AddPostModal
          selectedDate={selectedDate}
          onClose={closeModals}
        />
      )}

      {selectedPost && (
        <EventDetails post={selectedPost} onClose={closeModals} />
      )}
    </div>
  );
}

export default App;