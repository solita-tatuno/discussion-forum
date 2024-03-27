import { User, UserCredentials } from "../types";
import { getTokenFromLocalStorage } from "../utils";

const baseUrl =
  process.env.NODE_ENV === "dev"
    ? "http://localhost:8080/api/users"
    : "/api/users";

const createOne = async (userCredentials: UserCredentials): Promise<User> => {
  const response = await fetch(baseUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(userCredentials),
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.json();
};

const getCurrentUser = async (): Promise<User> => {
  const token = getTokenFromLocalStorage();

  const response = await fetch(`${baseUrl}/me`, {
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

export default { createOne, getCurrentUser };
