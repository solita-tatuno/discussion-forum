import {
  Topic,
  TopicUpdate,
  PaginationValues,
  PageableTopics,
  PageableMessages,
} from "../types";
import { getTokenFromLocalStorage } from "../utils";

const baseUrl =
  process.env.NODE_ENV === "dev"
    ? "http://localhost:8080/api/topics"
    : "/api/topics";

const getAll = async ({
  page,
  size,
}: PaginationValues): Promise<PageableTopics> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}?page=${page}&size=${size}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
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
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.json();
};

const deleteOne = async (id: number): Promise<void> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }
};

const updateOne = async ({ id, name }: TopicUpdate): Promise<Topic> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify({ name }),
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.json();
};

const getMessages = async (
  topicId: number,
  pagination: PaginationValues
): Promise<PageableMessages> => {
  const token = getTokenFromLocalStorage();
  const response = await fetch(
    `${baseUrl}/${topicId}/messages?page=${pagination.page}&size=${pagination.size}`,
    {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    }
  );

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.json();
};

const findOne = async (id: number): Promise<Topic> => {
  const token = getTokenFromLocalStorage();
  const response = await fetch(`${baseUrl}/${id}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.json();
};

export default {
  getAll,
  create,
  deleteOne,
  updateOne,
  getMessages,
  findOne,
};
