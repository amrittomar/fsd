import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  posts: [],
};

const postSlice = createSlice({
  name: "posts",

  initialState,

  reducers: {
    // CREATE
    addPost: (state, action) => {
      state.posts.push(action.payload);
    },

    // UPDATE
    editPost: (state, action) => {
      const { id, content, platform } = action.payload;

      const post = state.posts.find((post) => post.id === id);

      if (post) {
        post.content = content;
        post.platform = platform;
      }
    },

    // DELETE
    deletePost: (state, action) => {
      state.posts = state.posts.filter(
        (post) => post.id !== action.payload
      );
    },
  },
});

export const { addPost, editPost, deletePost } = postSlice.actions;

export default postSlice.reducer;