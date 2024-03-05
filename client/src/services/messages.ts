import { getTokenFromLocalStorage } from "../utils";
import { Message } from "../types";

const baseUrl = "http://localhost:8080/api/messages";

interface CreateMessagePayload {
  message: string;
  userId: number;
  topicId: number;
  upVotes: number;
}

const create = async (payload: CreateMessagePayload): Promise<Message> => {
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
  create,
};