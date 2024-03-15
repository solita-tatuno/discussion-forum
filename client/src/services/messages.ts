import { getTokenFromLocalStorage } from "../utils";
import { Message, MessageUpdate } from "../types";

const baseUrl = "http://localhost:8080/api/messages";

interface CreateMessagePayload {
  message: string;
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


const updateOne = async ({ id, message, userId, upVotes }: MessageUpdate): Promise<Message> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`,
    },
    body: JSON.stringify({ message, userId, upVotes }),
  });

  if (!response.ok) {
    const res = await response.json() as Error;
    throw new Error(res.message);
  }

  return response.json();
};

export default {
  create,
  updateOne,
};