import { Topic, SingleTopic, TopicUpdate, PaginationValues, PageableTopics } from "../types";
import { getTokenFromLocalStorage } from "../utils";

const baseUrl = "http://localhost:8080/api/topics";

const getAll = async ({ page, limit }: PaginationValues): Promise<PageableTopics> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}?page=${page}&size=${limit}`, {
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

const create = async (name: string): Promise<Topic> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(baseUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
  });

  if (!response.ok) {
    const res = await response.json() as Error;
    throw new Error(res.message);
  }

  return response.json();
};

const deleteOne = async (id: number): Promise<void> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
    method: "DELETE",
    headers: {
      "Authorization": `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    const res = await response.json() as Error;
    throw new Error(res.message);
  }
};

const updateOne = async ({ id, name }: TopicUpdate): Promise<Topic> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
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
  deleteOne,
  updateOne,
};