import { Topic } from "../types";
import { getTokenFromLocalStorage } from "../utils";

const baseUrl = "http://localhost:8080/api/topics";

const getAll = async (): Promise<Topic[]> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(baseUrl, {
    headers: {
      "Authorization": `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    const res = await response.json() as Error;
    throw new Error(res.message);
  }

  return response.json();
};

export default {
  getAll,
};