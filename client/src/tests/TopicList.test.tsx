import { describe, expect, test } from "vitest";
import { MemoryRouter } from "react-router-dom";
import { render, screen } from "@testing-library/react";

import TopicList from "../components/TopicList.tsx";
import { Topic } from "../types";
import { createQueryClientWrapper } from "./helper.tsx";

const topics: Topic[] = [
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
  },
];

describe("TopicList tests", () => {
  test("renders error message in case of error", () => {
    const errorMessage = "test error";
    render(<TopicList topics={[]} isPending={false} error={new Error(errorMessage)} />);

    expect(screen.getByText(errorMessage)).toBeDefined();
  });

  test("renders loading message if pending", () => {
    render(<TopicList topics={[]} isPending={true} error={null} />);
    expect(screen.getByText("Loading...")).toBeDefined();
  });

  test("renders topics when not pending an no error", () => {
    render(
      <MemoryRouter>
        <TopicList topics={topics} isPending={false} error={null} />
      </MemoryRouter>,
      {
        wrapper: createQueryClientWrapper(),
      },
    );

    topics.forEach((topic) => {
      expect(screen.getByText(topic.name)).toBeDefined();
      expect(screen.getByText(topic.user.username)).toBeDefined();
    });
  });
});