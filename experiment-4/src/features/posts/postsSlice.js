import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  posts: [
    {
      id: 1,
      title: "Product Launch Teaser",
      platform: "Instagram",
      date: "2026-09-10",
      time: "10:00",
    },
    {
      id: 2,
      title: "React Calendar Article",
      platform: "LinkedIn",
      date: "2026-09-15",
      time: "14:30",
    },
    {
      id: 3,
      title: "Weekend Offer",
      platform: "Facebook",
      date: "2026-09-20",
      time: "09:00",
    },
  ],
};

const postsSlice = createSlice({
  name: "posts",
  initialState,
  reducers: {
    addPost: (state, action) => {
      state.posts.push(action.payload);
    },

    updatePost: (state, action) => {
      const index = state.posts.findIndex(
        (post) => post.id === action.payload.id
      );

      if (index !== -1) {
        state.posts[index] = action.payload;
      }
    },

    movePost: (state, action) => {
      const { id, newDate } = action.payload;

      const post = state.posts.find((item) => item.id === id);

      if (post) {
        post.date = newDate;
      }
    },

    deletePost: (state, action) => {
      state.posts = state.posts.filter((post) => post.id !== action.payload);
    },
  },
});

export const { addPost, updatePost, movePost, deletePost } =
  postsSlice.actions;

export default postsSlice.reducer;