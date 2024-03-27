import { describe, expect, test } from "vitest";
import { render, screen } from "@testing-library/react";
import Message from "../components/Message";
import { formatDateString } from "../utils";
import { createQueryClientWrapper, testMessage } from "./helper.tsx";

describe("Message tests", () => {
  test("renders message user, message and created at", () => {
    render(<Message message={testMessage} />, {
      wrapper: createQueryClientWrapper(),
    });
    expect(screen.getByText(testMessage.user.username)).toBeDefined();
    expect(screen.getByText(testMessage.message)).toBeDefined();
    expect(screen.getByText(formatDateString(testMessage.createdAt))).toBeDefined();
  });
});
