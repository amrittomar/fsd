import { getCurrentUser } from "../utils/auth";

function Dashboard() {
  const user = getCurrentUser();
  return (
    <section className="card">
      <h1>Welcome, {user.name}</h1>
      <p className="muted">You are logged in as <strong>{user.role}</strong>. The navigation items and pages available to you are controlled by your role.</p>
    </section>
  );
}

export default Dashboard;
