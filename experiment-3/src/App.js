import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./components/Login";
import Navbar from "./components/Navbar";
import ProtectedRoute from "./components/ProtectedRoute";
import Admin from "./pages/Admin";
import Dashboard from "./pages/Dashboard";
import Editor from "./pages/Editor";
import Unauthorized from "./pages/Unauthorized";
import Viewer from "./pages/Viewer";
import { getCurrentUser } from "./utils/auth";

function App() {
  // Read the saved session once for this render. Login/logout reload the app,
  // so the value always reflects the current localStorage session.
  const currentUser = getCurrentUser();

  return (
    <div className="app-shell">
      {currentUser && <Navbar user={currentUser} />}

      <main className="page-container">
        <Routes>
          <Route
            path="/login"
            element={currentUser ? <Navigate to="/dashboard" replace /> : <Login />}
          />

          <Route element={<ProtectedRoute />}>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/viewer" element={<Viewer />} />
          </Route>

          <Route element={<ProtectedRoute allowedRoles={["Admin", "Editor"]} />}>
            <Route path="/editor" element={<Editor />} />
          </Route>

          <Route element={<ProtectedRoute allowedRoles={["Admin"]} />}>
            <Route path="/admin" element={<Admin />} />
          </Route>

          <Route path="/unauthorized" element={<Unauthorized />} />
          <Route path="*" element={<Navigate to="/dashboard" replace />} />
        </Routes>
      </main>
    </div>
  );
}

export default App;
