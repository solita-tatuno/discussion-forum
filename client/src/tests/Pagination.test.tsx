import { expect, describe, test, vi } from "vitest";
import { render, screen } from "@testing-library/react";
import Pagination from "../components/Pagination";
import { renderWithUser } from "./helper.tsx";

describe("Pagination tests", () => {
  test("renders correct page count", () => {
    render(
      <Pagination itemCount={16} page={1} setPage={() => null} size={5} />
    );
    expect(screen.getByText("1")).toBeDefined();
    expect(screen.getByText("2")).toBeDefined();
    expect(screen.getByText("3")).toBeDefined();
    expect(screen.getByText("4")).toBeDefined();
    expect(screen.queryByText("5")).toBeNull();
  });

  test("set page is called with correct value when next link is clicked", async () => {
    const setPage = vi.fn();

    const { user } = renderWithUser(
      <Pagination itemCount={15} page={1} setPage={setPage} size={5} />
    );

    const nextButton = screen.getByText(">");

    await user.click(nextButton);
    expect(setPage).toHaveBeenCalledWith(2);

    await user.click(nextButton);
    expect(setPage).toHaveBeenCalledWith(3);
  });

  test.each([4, 2, 3])(
    "setPage is called with correct value when page number %s is clicked",
    async (pageNumber) => {
      const setPage = vi.fn();

      const { user } = renderWithUser(
        <Pagination itemCount={16} page={1} setPage={setPage} size={5} />
      );
      const pageButton = screen.getByText(pageNumber);

      await user.click(pageButton);

      expect(setPage).toHaveBeenCalledWith(pageNumber);
    }
  );

  test.each([
    { page: 1, expectedText: "Showing 1 to 5 of 16 results" },
    { page: 2, expectedText: "Showing 6 to 10 of 16 results" },
    { page: 3, expectedText: "Showing 11 to 15 of 16 results" },
    { page: 4, expectedText: "Showing 16 to 16 of 16 results" },
  ])("renders correct range text for page $page", ({ page, expectedText }) => {
    render(
      <Pagination itemCount={16} page={page} setPage={() => null} size={5} />
    );
    expect(screen.getByText(expectedText)).toBeDefined();
  });
});
