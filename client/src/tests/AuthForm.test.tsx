import { describe, expect, test, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import AuthForm from "../components/AuthForm";
import { renderWithUser } from "./helper.tsx";

describe("AuthForm tests", () => {
  test("initially renders empty form", () => {
    render(<AuthForm handleSubmit={() => null} />);
    const usernameInput = screen.getByPlaceholderText("username");
    const passwordInput = screen.getByPlaceholderText("password");

    expect(usernameInput).toBeDefined();
    expect(passwordInput).toBeDefined();

    expect(passwordInput.textContent).toBe("");
    expect(usernameInput.textContent).toBe("");
  });

  test("calls handleSubmit on form submission", async () => {
    const handleSubmit = vi.fn();

    const { user } = renderWithUser(<AuthForm handleSubmit={handleSubmit} />);

    const usernameInput = screen.getByPlaceholderText("username");
    const passwordInput = screen.getByPlaceholderText("password");
    const submitButton = screen.getByText("Submit");

    const expectedValues = { username: "username", password: "password" };

    await user.type(usernameInput, expectedValues.username);
    await user.type(passwordInput, expectedValues.password);

    await user.click(submitButton);

    expect(handleSubmit).toHaveBeenCalledOnce();
    expect(handleSubmit).toHaveBeenCalledWith(expectedValues);
  });

  test("renders error message if username or password is empty", async () => {
    const handleSubmit = vi.fn();

    const { user } = renderWithUser(<AuthForm handleSubmit={handleSubmit} />);

    const submitButton = screen.getByText("Submit");

    await user.click(submitButton);

    expect(screen.getByText("Username required")).toBeDefined();
    expect(screen.getByText("Password required")).toBeDefined();
  });
});
