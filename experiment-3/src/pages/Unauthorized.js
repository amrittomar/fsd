import { Link } from "react-router-dom";

function Unauthorized() {
  return <section className="card"><h1>Access Denied</h1><p className="muted">Your role does not have permission to open this page.</p><Link className="action-link" to="/dashboard">Return to dashboard</Link></section>;
}

export default Unauthorized;
