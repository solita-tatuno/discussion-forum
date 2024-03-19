import { vi, describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import CreateMessage from "../components/CreateMessage";
import { createQueryClientWrapper } from "./helper";
import * as useCreateMessage from "../hooks/useCreateMessage.ts";

describe("CreateMessage", () => {
  test("renders correctly", () => {
    render(<CreateMessage topicId={1} />, {
      wrapper: createQueryClientWrapper(),
    });
    const textarea = screen.getByPlaceholderText("Create new message");
    const button = screen.getByText("Create");

    expect(textarea).toBeDefined();
    expect(button).toBeDefined();
  });

  test("calls handleCreateMessage on button click once with correct values", async () => {
    const spy = vi.spyOn(useCreateMessage, "default");
    const mockHandleCreateMessage = vi.fn();
    const user = userEvent.setup();
    const topicId = 1;

    spy.mockReturnValue({
      createMessage: mockHandleCreateMessage,
    });

    render(<CreateMessage topicId={topicId} />, {
      wrapper: createQueryClientWrapper(),
    });

    const button = screen.getByText("Create");
    const textarea = screen.getByPlaceholderText("Create new message");
    const expectedMessage = "new message";

    await user.type(textarea, expectedMessage);
    await user.click(button);

    expect(mockHandleCreateMessage).toHaveBeenCalledOnce();
    expect(mockHandleCreateMessage).toHaveBeenCalledWith({
      message: expectedMessage,
      topicId,
    });
  });
});
