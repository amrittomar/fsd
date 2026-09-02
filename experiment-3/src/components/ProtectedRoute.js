import { Navigate, Outlet, useLocation } from "react-router-dom";
import { getCurrentUser } from "../utils/auth";

function ProtectedRoute({ allowedRoles }) {
  const user = getCurrentUser();
  const location = useLocation();

  // Users without a session are sent to login.
  if (!user) return <Navigate to="/login" replace state={{ from: location }} />;

  // If roles are supplied, the user's role must be in the allowed list.
  if (allowedRoles && !allowedRoles.includes(user.role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  // Outlet renders the child Route nested inside this route.
  return <Outlet />;
}

export default ProtectedRoute;
