import { expect, describe, test, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import CreateTopic from "../components/CreateTopic";
import * as useCreateTopic from "../hooks/useCreateTopic";
import { createQueryClientWrapper, renderWithUser } from "./helper.tsx";

describe("useCreateTopic tests", () => {
  const spy = vi.spyOn(useCreateTopic, "default");
  const handleSubmit = vi.fn();

  test("renders correctly", () => {
    spy.mockReturnValue({
      createTopic: handleSubmit,
    });

    render(<CreateTopic />, {
      wrapper: createQueryClientWrapper(),
    });

    const input = screen.getByPlaceholderText("Create new topic");
    const button = screen.getByText("Create");

    expect(input).toBeDefined();
    expect(button).toBeDefined();
  });

  test("calls createTopic once with correct values", async () => {
    const newTopicName = "new topic";

    spy.mockReturnValue({
      createTopic: handleSubmit,
    });

    const { user } = renderWithUser(<CreateTopic />, {
      wrapper: createQueryClientWrapper(),
    });

    const input = screen.getByPlaceholderText("Create new topic");
    const button = screen.getByText("Create");

    await user.type(input, newTopicName);
    await user.click(button);

    expect(handleSubmit).toHaveBeenCalledOnce();
    expect(handleSubmit).toHaveBeenCalledWith(newTopicName);
  });
});
