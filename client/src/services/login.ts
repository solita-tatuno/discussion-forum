import { UserCredentials } from "../types";

const baseUrl = "http://localhost:8080/api/login";

const login = async ({
  username,
  password,
}: UserCredentials): Promise<String> => {
  const response = await fetch(baseUrl, {
    method: "POST",
    headers: {
      Authorization: `Basic ${btoa(`${username}:${password}`)}`,
    },
  });

  if (!response.ok) {
    const res = (await response.json()) as Error;
    throw new Error(res.message);
  }

  return response.text();
};

export default { login };
