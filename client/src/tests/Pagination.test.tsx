import { expect, describe, test, vi } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import Pagination from "../components/Pagination";

describe("useCreateTopic tests", () => {

  test("renders correct page count", () => {
    render(<Pagination itemCount={16} page={0} setPage={() => null} limit={5} />);
    expect(screen.getByText("1")).toBeDefined();
    expect(screen.getByText("2")).toBeDefined();
    expect(screen.getByText("3")).toBeDefined();
    expect(screen.getByText("4")).toBeDefined();
  });

  test("set page is called with correct value when page number is clicked", () => {
    const setPage = vi.fn();

    render(<Pagination itemCount={15} page={0} setPage={setPage} limit={5} />);

    fireEvent.click(screen.getByText("2"));
    expect(setPage).toHaveBeenCalledWith(1);

    fireEvent.click(screen.getByText("3"));
    expect(setPage).toHaveBeenCalledWith(2);
  });

  test("renders correct range text", async () => {
    const testCases = [
      { page: 0, expectedText: "Showing 1 to 5 of 16 results" },
      { page: 1, expectedText: "Showing 6 to 10 of 16 results" },
      { page: 2, expectedText: "Showing 11 to 15 of 16 results" },
      { page: 3, expectedText: "Showing 16 to 16 of 16 results" },
    ];

    testCases.forEach(({ page, expectedText }) => {
      render(<Pagination itemCount={16} page={page} setPage={() => null} limit={5} />);
      expect(screen.getByText(expectedText)).toBeDefined();
    });
  });
});