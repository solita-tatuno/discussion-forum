import { expect, describe, test, vi } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import Pagination from "../components/Pagination";

describe("Pagination tests", () => {

  test("renders correct page count", () => {
    render(<Pagination itemCount={16} page={0} setPage={() => null} size={5} />);
    expect(screen.getByText("1")).toBeDefined();
    expect(screen.getByText("2")).toBeDefined();
    expect(screen.getByText("3")).toBeDefined();
    expect(screen.getByText("4")).toBeDefined();
  });

  test("set page is called with correct value when next link is clicked", async () => {
    const setPage = vi.fn();

    render(<Pagination itemCount={15} page={1} setPage={setPage} size={5} />);

    fireEvent.click(screen.getByText(">"));
    expect(setPage).toHaveBeenCalledWith(2);


    fireEvent.click(screen.getByText(">"));
    expect(setPage).toHaveBeenCalledWith(3);
  });

  test("renders correct range text", async () => {
    const testCases = [
      { page: 1, expectedText: "Showing 1 to 5 of 16 results" },
      { page: 2, expectedText: "Showing 6 to 10 of 16 results" },
      { page: 3, expectedText: "Showing 11 to 15 of 16 results" },
      { page: 4, expectedText: "Showing 16 to 16 of 16 results" },
    ];

    testCases.forEach(({ page, expectedText }) => {
      render(<Pagination itemCount={16} page={page} setPage={() => null} size={5} />);
      expect(screen.getByText(expectedText)).toBeDefined();
    });
  });
});