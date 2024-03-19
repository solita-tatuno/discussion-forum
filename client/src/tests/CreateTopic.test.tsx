import { expect, describe, test, vi } from "vitest";
import { render, screen, fireEvent, act } from "@testing-library/react";
import CreateTopic from "../components/CreateTopic";
import * as useCreateTopic from "../hooks/useCreateTopic";
import { createQueryClientWrapper } from "./helper.tsx";

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

    expect(screen.getByPlaceholderText("Create new topic")).toBeDefined();
    expect(screen.getByText("Create")).toBeDefined();
  });

  test("calls createTopic once with correct values", async () => {
    const newTopicName = "new topic";

    spy.mockReturnValue({
      createTopic: handleSubmit,
    });

    render(<CreateTopic />, {
      wrapper: createQueryClientWrapper(),
    });

    const input = screen.getByPlaceholderText("Create new topic");
    const button = screen.getByText("Create");

    act(() => {
      fireEvent.change(input, {
        target: {
          value: newTopicName,
        },
      });

      button.click();
    });

    expect(handleSubmit).toHaveBeenCalledOnce();
    expect(handleSubmit).toHaveBeenCalledWith(newTopicName);
  });
});
