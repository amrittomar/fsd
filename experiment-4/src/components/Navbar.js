function Navbar({ onSchedulePost }) {
  return (
    <header className="navbar">
      <div>
        <p className="experiment-label">Experiment 4</p>
        <h1>Social Media Post Scheduler</h1>
        <p className="navbar-subtitle">
          Plan, edit, and reschedule posts using an interactive calendar.
        </p>
      </div>

      <button className="schedule-button" onClick={onSchedulePost}>
        + Schedule Post
      </button>
    </header>
  );
}

export default Navbar;