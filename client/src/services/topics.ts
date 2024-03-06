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

const findOne = async (id: number): Promise<SingleTopic> => {
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

interface CreateTopicPayload {
  name: string;
  userId: number;
}


const create = async (payload: CreateTopicPayload): Promise<Topic> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(baseUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`,
    },
    body: JSON.stringify(payload),
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
  create,
};