export const getTokenFromLocalStorage = () => {
  const token = localStorage.getItem("df-token");
  return token ? JSON.parse(token) : null;
};

export const formatDateString = (date: string | undefined) => {
  return date ? new Date(date).toLocaleString() : "N/A";
};
