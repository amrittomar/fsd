// This is a front-end learning demo. A real application must validate JWTs
// on a secure server and must never keep real passwords in source code.
const STORAGE_KEY = "experiment3_mock_jwt";

const USERS = [
  { username: "admin", password: "1234", role: "Admin", name: "Admin User" },
  { username: "editor", password: "1234", role: "Editor", name: "Editor User" },
  { username: "viewer", password: "1234", role: "Viewer", name: "Viewer User" },
];

// Creates a token-shaped string only to demonstrate a JWT-style workflow.
// It is not cryptographically signed and is not safe for production use.
const createMockToken = (user) => {
  const payload = btoa(JSON.stringify({ username: user.username, role: user.role }));
  return `mock-jwt.${payload}.signature`;
};

export const login = (username, password) => {
  const user = USERS.find(
    (item) => item.username === username.trim().toLowerCase() && item.password === password
  );

  if (!user) return { success: false, message: "Invalid username or password." };

  const session = {
    token: createMockToken(user),
    user: { username: user.username, name: user.name, role: user.role },
  };

  localStorage.setItem(STORAGE_KEY, JSON.stringify(session));
  return { success: true, user: session.user };
};

export const logout = () => localStorage.removeItem(STORAGE_KEY);

export const getCurrentUser = () => {
  try {
    const session = JSON.parse(localStorage.getItem(STORAGE_KEY));
    return session?.token && session?.user ? session.user : null;
  } catch {
    // Invalid saved data is treated as a logged-out session.
    return null;
  }
};

export const isAuthenticated = () => Boolean(getCurrentUser());
