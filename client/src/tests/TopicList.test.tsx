import { describe, expect, test } from "vitest";
import { MemoryRouter } from "react-router-dom";
import { render, screen } from "@testing-library/react";

import TopicList from "../components/TopicList.tsx";
import { Topic } from "../types";

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
  },
];

describe("TopicList tests", () => {
  test("renders empty list if 0 topics", () => {
    render(<TopicList topics={[]} isPending={false} error={null} />);

    expect(screen.getByText("Topic")).toBeDefined();
    expect(screen.getByText("Creator")).toBeDefined();
  });

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
    );

    topics.forEach((topic) => {
      expect(screen.getByText(topic.name)).toBeDefined();
      expect(screen.getByText(topic.user.username)).toBeDefined();
    });
  });
});