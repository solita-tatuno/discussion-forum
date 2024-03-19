import { describe, expect, test } from "vitest";
import { render, screen } from "@testing-library/react";
import TopicDetails from "../components/TopicDetails.tsx";
import { testTopic } from "./helper.tsx";
import { formatDateString } from "../utils";

describe("TopicDetails tests", () => {
  test("renders topic name, user, message count and time of last message", () => {
    render(<TopicDetails topic={testTopic} />);
    expect(screen.getByText(testTopic.name)).toBeDefined();
    expect(screen.getByText(testTopic.user.username)).toBeDefined();
    expect(screen.getByText(testTopic.messageCount)).toBeDefined();
    expect(
      screen.getByText(formatDateString(testTopic.lastMessageTime))
    ).toBeDefined();
  });
});
