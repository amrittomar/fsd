import { useState } from "react";
import { login } from "../utils/auth";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();
    const result = login(username, password);

    if (!result.success) {
      setError(result.message);
      return;
    }

    // A reload updates App and the navbar after localStorage changes.
    window.location.replace("/dashboard");
  };

  return (
    <section className="card login-card">
      <h1>Experiment 3 Login</h1>
      <p className="muted">Sign in to see role-based protected routes.</p>
      <form onSubmit={handleSubmit}>
        <label className="form-group">
          Username
          <input value={username} onChange={(e) => setUsername(e.target.value)} autoComplete="username" required />
        </label>
        <label className="form-group">
          Password
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} autoComplete="current-password" required />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit">Login</button>
      </form>
      <div className="credentials">
        <strong>Demo credentials</strong><br />
        admin / 1234 &mdash; Admin<br />
        editor / 1234 &mdash; Editor<br />
        viewer / 1234 &mdash; Viewer
      </div>
    </section>
  );
}

export default Login;
