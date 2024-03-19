import { describe, expect, test } from "vitest";
import { render, screen } from "@testing-library/react";
import Message from "../components/Message";
import { formatDateString } from "../utils";
import { Message as MessageType } from "../types";
import { createQueryClientWrapper } from "./helper.tsx";

describe("Message tests", () => {
  test("renders message user, message and created at", () => {
    const message: MessageType = {
      id: 1,
      topicId: 1,
      upVotes: 0,
      message: "test message",
      createdAt: new Date().toISOString(),
      user: {
        id: 1,
        username: "test user",
        isAdmin: false,
      },
    };
    render(<Message message={message} />, {
      wrapper: createQueryClientWrapper(),
    });
    expect(screen.getByText(message.user.username)).toBeDefined();
    expect(screen.getByText(message.message)).toBeDefined();
    expect(screen.getByText(formatDateString(message.createdAt))).toBeDefined();
  });
});
