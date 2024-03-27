import { test, vi, expect, describe } from "vitest";
import { render, screen } from "@testing-library/react";
import * as useCurrentUser from "../hooks/useCurrentUser";
import * as useUpdateTopic from "../hooks/useUpdateTopic";
import * as useDeleteTopic from "../hooks/useDeleteTopic";
import TopicActions from "../components/TopicActions.tsx";
import {
  createQueryClientWrapper,
  renderWithUser,
  testTopic,
} from "./helper.tsx";

describe("TopicActions tests", () => {
  test("renders null if current user is not admin", () => {
    const spy = vi.spyOn(useCurrentUser, "default");
    spy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: false,
      },
      isPending: false,
      isError: false,
    });

    const { container } = render(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    expect(container.childElementCount).toBe(0);
  });

  test("renders edit, delete when user is admin", async () => {
    const spy = vi.spyOn(useCurrentUser, "default");
    spy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: true,
      },
      isPending: false,
      isError: false,
    });

    render(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    expect(screen.getByText("Edit")).toBeDefined();
    expect(screen.getByText("Delete")).toBeDefined();
  });

  test("on edit click edit modal is opened and input contains clicked topic name", async () => {
    const spy = vi.spyOn(useCurrentUser, "default");
    spy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: true,
      },
      isPending: false,
      isError: false,
    });

    const { user } = renderWithUser(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    const editButton = screen.getByText("Edit");
    await user.click(editButton);

    const input = screen.getByPlaceholderText("New topic name");

    expect(screen.getByText("Update Topic")).toBeDefined();
    expect((input as HTMLInputElement).value).toBe(testTopic.name);
  });

  test("on update click updateTopic is called with correct values", async () => {
    const userSpy = vi.spyOn(useCurrentUser, "default");
    const updateTopicSpy = vi.spyOn(useUpdateTopic, "default");
    const handleSubmit = vi.fn();

    userSpy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: true,
      },
      isPending: false,
      isError: false,
    });

    updateTopicSpy.mockReturnValue({
      updateTopic: handleSubmit,
    });

    const { user } = renderWithUser(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    const expectedMessage = "expected message";

    const editButton = screen.getByText("Edit");
    await user.click(editButton);

    const input = screen.getByPlaceholderText("New topic name");
    await user.clear(input);
    await user.type(input, expectedMessage);

    const updateButton = screen.getByText("Update");
    await user.click(updateButton);

    expect(handleSubmit).toHaveBeenCalledOnce();
    expect(handleSubmit).toHaveBeenCalledWith({
      id: testTopic.id,
      name: expectedMessage,
    });
  });

  test("on delete click deleteTopic is called with correct values when confirmed", async () => {
    const userSpy = vi.spyOn(useCurrentUser, "default");
    const deleteTopicSpy = vi.spyOn(useDeleteTopic, "default");
    const handleSubmit = vi.fn();

    window.confirm = vi.fn(() => true);

    userSpy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: true,
      },
      isPending: false,
      isError: false,
    });

    deleteTopicSpy.mockReturnValue({
      deleteTopic: handleSubmit,
    });

    const { user } = renderWithUser(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    const deleteButton = screen.getByText("Delete");

    await user.click(deleteButton);
    expect(handleSubmit).toHaveBeenCalledOnce();
    expect(handleSubmit).toHaveBeenCalledWith(testTopic.id);
  });

  test("on delete click deleteTopic is not called when unconfirmed", async () => {
    const userSpy = vi.spyOn(useCurrentUser, "default");
    const deleteTopicSpy = vi.spyOn(useDeleteTopic, "default");
    const handleSubmit = vi.fn();

    window.confirm = vi.fn(() => false);

    userSpy.mockReturnValue({
      currentUser: {
        id: 1,
        username: "test user",
        isAdmin: true,
      },
      isPending: false,
      isError: false,
    });

    deleteTopicSpy.mockReturnValue({
      deleteTopic: handleSubmit,
    });

    const { user } = renderWithUser(<TopicActions topic={testTopic} />, {
      wrapper: createQueryClientWrapper(),
    });

    const deleteButton = screen.getByText("Delete");

    await user.click(deleteButton);
    expect(handleSubmit).toHaveBeenCalledTimes(0);
  });
});
