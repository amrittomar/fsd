import React, { useState } from "react";
import { useSelector, useDispatch } from "react-redux";

import { addPost, editPost, deletePost } from "./features/posts/postSlice";
import { setPlatform } from "./features/platform/platformSlice";

import "./App.css";

function App() {
  const dispatch = useDispatch();

  const posts = useSelector((state) => state.posts.posts);
  const selectedPlatform = useSelector(
    (state) => state.platform.selectedPlatform
  );

  const [post, setPost] = useState("");
  const [editId, setEditId] = useState(null);

  const handleSubmit = () => {
    if (post.trim() === "") return;

    if (editId !== null) {
      dispatch(
        editPost({
          id: editId,
          content: post,
          platform: selectedPlatform,
        })
      );
      setEditId(null);
    } else {
      dispatch(
        addPost({
          id: Date.now(),
          content: post,
          platform: selectedPlatform,
        })
      );
    }

    setPost("");
  };

  const handleEdit = (item) => {
    setPost(item.content);
    setEditId(item.id);
    dispatch(setPlatform(item.platform));
  };

  return (
    <div className="container">
      <h1>Redux Toolkit - Post Manager</h1>

      <textarea
        placeholder="Write your post..."
        value={post}
        onChange={(e) => setPost(e.target.value)}
      />

      <br />

      <select
        value={selectedPlatform}
        onChange={(e) => dispatch(setPlatform(e.target.value))}
      >
        <option>Facebook</option>
        <option>Instagram</option>
        <option>Twitter</option>
        <option>LinkedIn</option>
      </select>

      <br />

      <button onClick={handleSubmit}>
        {editId !== null ? "Update Post" : "Add Post"}
      </button>

      <hr />

      <h2>Posts</h2>

      {posts.length === 0 ? (
        <p>No Posts Available</p>
      ) : (
        posts.map((item) => (
          <div key={item.id} className="post-card">
            <h3>{item.platform}</h3>

            <p>{item.content}</p>

            <button onClick={() => handleEdit(item)}>
              Edit
            </button>

            <button
              onClick={() => dispatch(deletePost(item.id))}
            >
              Delete
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default App;