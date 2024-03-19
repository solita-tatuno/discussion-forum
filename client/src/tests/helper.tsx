import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { ReactNode } from "react";
import { Topic } from "../types";
import { render } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

export const createQueryClientWrapper = () => {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        retry: false,
      },
    },
  });
  return ({ children }: { children: ReactNode }) => (
    <QueryClientProvider client={queryClient}>{children}</QueryClientProvider>
  );
};

// @ts-ignore
export const renderWithUser = (jsx, opts?) => {
  return {
    user: userEvent.setup(),
    ...render(jsx, { ...opts }),
  };
};

export const testTopics: Topic[] = [
  {
    id: 1,
    name: "test topic",
    createdAt: "",
    user: {
      id: 1,
      username: "test user",
      isAdmin: false,
    },
    messageCount: 0,
    lastMessageTime: new Date().toISOString(),
  },
  {
    id: 2,
    name: "test topic 2",
    createdAt: "",
    user: {
      id: 2,
      username: "test user 2",
      isAdmin: false,
    },
    messageCount: 0,
    lastMessageTime: new Date().toISOString(),
  },
];
