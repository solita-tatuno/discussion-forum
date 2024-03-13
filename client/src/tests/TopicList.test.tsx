import { describe, expect, test } from "vitest";
import { MemoryRouter } from "react-router-dom";
import { render, screen } from "@testing-library/react";

import TopicList from "../components/TopicList.tsx";
import { testTopics } from "./helper.tsx";
import { createQueryClientWrapper } from "./helper.tsx";


describe("TopicList tests", () => {
  test("renders loading message if pending", () => {
    render(<TopicList topics={[]} isPending={true}  />);
    expect(screen.getByText("Loading...")).toBeDefined();
  });

  test("renders topics when not pending", () => {
    render(
      <MemoryRouter>
        <TopicList topics={testTopics} isPending={false} />
      </MemoryRouter>,
      {
        wrapper: createQueryClientWrapper(),
      },
    );

    testTopics.forEach((topic) => {
      expect(screen.getByText(topic.name)).toBeDefined();
      expect(screen.getByText(topic.user.username)).toBeDefined();
    });
  });
});