import { Topic, SingleTopic } from "../types";
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

const findOne = async (id: string): Promise<SingleTopic> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
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
  findOne,
};