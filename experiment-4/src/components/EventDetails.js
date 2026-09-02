import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import {
  deletePost,
  updatePost,
} from "../features/posts/postsSlice";

function EventDetails({ post, onClose }) {
  const dispatch = useDispatch();

  const [title, setTitle] = useState("");
  const [platform, setPlatform] = useState("");
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");

  // Fill the form with the selected post's current information.
  useEffect(() => {
    if (post) {
      setTitle(post.title);
      setPlatform(post.platform);
      setDate(post.date);
      setTime(post.time);
    }
  }, [post]);

  if (!post) {
    return null;
  }

  const handleUpdate = (event) => {
    event.preventDefault();

    dispatch(
      updatePost({
        id: post.id,
        title,
        platform,
        date,
        time,
      })
    );

    onClose();
  };

  const handleDelete = () => {
    const shouldDelete = window.confirm(
      "Are you sure you want to delete this post?"
    );

    if (shouldDelete) {
      dispatch(deletePost(post.id));
      onClose();
    }
  };

  return (
    <div className="modal-overlay">
      <div className="modal-box">
        <div className="modal-header">
          <h2>Edit Scheduled Post</h2>

          <button className="close-button" onClick={onClose}>
            ×
          </button>
        </div>

        <form onSubmit={handleUpdate}>
          <label>
            Post Title
            <input
              type="text"
              value={title}
              onChange={(event) => setTitle(event.target.value)}
              required
            />
          </label>

          <label>
            Platform
            <select
              value={platform}
              onChange={(event) => setPlatform(event.target.value)}
            >
              <option value="Instagram">Instagram</option>
              <option value="LinkedIn">LinkedIn</option>
              <option value="Facebook">Facebook</option>
              <option value="X">X</option>
            </select>
          </label>

          <label>
            Date
            <input
              type="date"
              value={date}
              onChange={(event) => setDate(event.target.value)}
              required
            />
          </label>

          <label>
            Time
            <input
              type="time"
              value={time}
              onChange={(event) => setTime(event.target.value)}
              required
            />
          </label>

          <div className="modal-actions">
            <button type="button" className="delete-button" onClick={handleDelete}>
              Delete
            </button>

            <button type="submit">Save Changes</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EventDetails;