export const getTokenFromLocalStorage = () => {
  const token = localStorage.getItem("df-token");
  return token ? JSON.parse(token) : null;
}

export const formatDateString = (date: string) => {
  return new Date(date).toLocaleString();
}
