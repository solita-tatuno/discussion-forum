import { describe, expect, test, vi } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import AuthForm from "../components/AuthForm";

describe("AuthForm tests", () => {
  test("initially renders empty form", () => {
    render(<AuthForm handleSubmit={() => null} error={null} />);
    const usernameInput = screen.getByPlaceholderText("username");
    const passwordInput = screen.getByPlaceholderText("password");

    expect(passwordInput.textContent).toBe("");
    expect(usernameInput.textContent).toBe("");
  });

  test("renders error message in case of error", () => {
    const errorMessage = "test error";
    render(<AuthForm handleSubmit={() => null} error={new Error(errorMessage)} />);

    expect(screen.getByText(errorMessage)).toBeDefined();
  });

  test("calls handleSubmit on form submission", async () => {
    const handleSubmit = vi.fn();

    render(<AuthForm handleSubmit={handleSubmit} error={null} />);

    const usernameInput = screen.getByPlaceholderText("username");
    const passwordInput = screen.getByPlaceholderText("password");

    const expectedValues = { username: "username", password: "password" };

    fireEvent.change(usernameInput, { target: { value: expectedValues.username } });
    fireEvent.change(passwordInput, { target: { value: expectedValues.password } });

    screen.getByText("Submit").click();

    await vi.waitFor(() => {
      expect(handleSubmit).toHaveBeenCalledOnce();
      expect(handleSubmit).toHaveBeenCalledWith(expectedValues);
    });
  });
});