import { NavLink, useNavigate } from "react-router-dom";
import { logout } from "../utils/auth";

function Navbar({ user }) {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login", { replace: true });
    window.location.reload();
  };

  return (
    <nav className="navbar">
      <NavLink className="brand" to="/dashboard">Experiment 3</NavLink>
      <div className="nav-links">
        <NavLink to="/dashboard">Dashboard</NavLink>
        {user.role === "Admin" && <NavLink to="/admin">Admin</NavLink>}
        {["Admin", "Editor"].includes(user.role) && <NavLink to="/editor">Editor</NavLink>}
        <NavLink to="/viewer">Viewer</NavLink>
        <span className="role-badge">{user.role}</span>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
      </div>
    </nav>
  );
}

export default Navbar;
