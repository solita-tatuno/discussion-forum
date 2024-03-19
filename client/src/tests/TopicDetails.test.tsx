import { describe, expect, test } from "vitest";
import { render, screen } from "@testing-library/react";
import TopicDetails from "../components/TopicDetails.tsx";
import { testTopics } from "./helper.tsx";
import { formatDateString } from "../utils";

describe("TopicDetails tests", () => {
  const topic = testTopics[0];
  test("renders topic name, user, message count and time of last message", () => {
    render(<TopicDetails topic={topic} />);
    expect(screen.getByText(topic.name)).toBeDefined();
    expect(screen.getByText(topic.user.username)).toBeDefined();
    expect(screen.getByText(topic.messageCount)).toBeDefined();
    expect(
      screen.getByText(formatDateString(topic.lastMessageTime))
    ).toBeDefined();
  });
});

