import { describe, expect, test } from "vitest";
import { MemoryRouter } from "react-router-dom";
import { render, screen } from "@testing-library/react";

import TopicList from "../components/TopicList.tsx";
import { testTopics } from "./helper.tsx";
import { createQueryClientWrapper } from "./helper.tsx";

describe.skip("TopicList tests", () => {
  test("renders topics", () => {
    render(
      <MemoryRouter>
        <TopicList topics={testTopics} />
      </MemoryRouter>,
      {
        wrapper: createQueryClientWrapper(),
      }
    );

    testTopics.forEach((topic) => {
      expect(screen.getByText(topic.name)).toBeDefined();
      expect(screen.getByText(topic.user.username)).toBeDefined();
    });
  });
});
