export const getTokenFromLocalStorage = () => {
  const token = localStorage.getItem("df-token");
  return token ? JSON.parse(token) : null;
}
