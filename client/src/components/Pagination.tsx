import { Dispatch } from "react";
import ReactPaginate from "react-paginate";

interface Props {
  itemCount: number;
  page: number;
  setPage: Dispatch<number>;
  limit: number;
}

function Pagination({ itemCount, page, setPage, limit }: Props) {
  const pageCount = Math.ceil(itemCount / limit);
  const first = Math.min((page - 1) * limit + 1);
  const last = Math.min(page * limit, itemCount);

  const handlePageClick = ({ selected }: { selected: number }) => {
    setPage(selected + 1);
  };

  return (
    <>
      <ReactPaginate
        containerClassName="flex list-none border border-gray-200 rounded-lg overflow-hidden p-3"

        pageClassName="inline-flex items-center justify-center px-3 border-r border-gray-200"
        pageLinkClassName="no-underline text-black"

        activeClassName="bg-gray-400 rounded-md text-white"
        activeLinkClassName="no-underline text-black"

        previousClassName="inline-flex items-center justify-center px-3 border-r border-gray-200"
        previousLinkClassName="no-underline text-black"

        nextClassName="inline-flex items-center px-3 justify-center"
        nextLinkClassName="no-underline text-black"

        breakClassName="inline-flex items-center justify-center px-3 border-r border-gray-200"
        breakLinkClassName="no-underline text-black"
        breakLabel="..."
        nextLabel=">"
        onPageChange={handlePageClick}
        pageRangeDisplayed={2}
        marginPagesDisplayed={1}
        pageCount={pageCount}
        previousLabel="<"
        renderOnZeroPageCount={null}
      />
      <p>
        Showing {first} to {last} of {itemCount} results
      </p>
    </>
  )
    ;
}

export default Pagination;