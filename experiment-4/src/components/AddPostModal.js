import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { addPost } from "../features/posts/postsSlice";

function AddPostModal({ selectedDate, onClose }) {
  const dispatch = useDispatch();

  const [title, setTitle] = useState("");
  const [platform, setPlatform] = useState("Instagram");
  const [time, setTime] = useState("10:00");

  // Reset the form whenever the user opens the modal for a new date.
  useEffect(() => {
    setTitle("");
    setPlatform("Instagram");
    setTime("10:00");
  }, [selectedDate]);

  const handleSubmit = (event) => {
    event.preventDefault();

    dispatch(
      addPost({
        id: Date.now(),
        title,
        platform,
        date: selectedDate,
        time,
      })
    );

    onClose();
  };

  return (
    <div className="modal-overlay">
      <div className="modal-box">
        <div className="modal-header">
          <h2>Schedule New Post</h2>

          <button className="close-button" onClick={onClose}>
            ×
          </button>
        </div>

        <p>
          Selected date: <strong>{selectedDate}</strong>
        </p>

        <form onSubmit={handleSubmit}>
          <label>
            Post Title
            <input
              type="text"
              value={title}
              placeholder="Enter post title"
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
            Time
            <input
              type="time"
              value={time}
              onChange={(event) => setTime(event.target.value)}
              required
            />
          </label>

          <div className="modal-actions">
            <button type="button" className="cancel-button" onClick={onClose}>
              Cancel
            </button>

            <button type="submit">Schedule Post</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddPostModal;