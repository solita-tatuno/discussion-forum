import { vi, expect, describe, test } from "vitest";
import { render, screen } from "@testing-library/react";
import Modal from "../components/Modal";
import { renderWithUser } from "./helper.tsx";

describe("Modal tests", () => {
  const children = <div>Test</div>;

  test("renders null when isOpen is false", () => {
    const { container } = render(
      <Modal isOpen={false} onClose={() => null} children={children} />
    );
    expect(container.childElementCount).toBe(0);
  });

  test("renders children correctly when open", () => {
    render(<Modal isOpen={true} onClose={() => null} children={children} />);
    expect(screen.getByText("Test")).toBeDefined();
  });

  test("calls onClose when close button is clicked", async () => {
    const onClose = vi.fn();
    const { user } = renderWithUser(
      <Modal isOpen={true} onClose={onClose} children={children} />
    );

    const closeButton = screen.getByText("×");
    await user.click(closeButton);

    expect(onClose).toHaveBeenCalled();
  });
});
