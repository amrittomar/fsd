import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  selectedPlatform: "Facebook",
};

const platformSlice = createSlice({
  name: "platform",

  initialState,

  reducers: {
    setPlatform: (state, action) => {
      state.selectedPlatform = action.payload;
    },
  },
});

export const { setPlatform } = platformSlice.actions;

export default platformSlice.reducer;