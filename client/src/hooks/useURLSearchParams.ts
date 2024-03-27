import { useSearchParams } from "react-router-dom";

interface Params {
  initialPage: string;
}

const useURLSearchParams = ({ initialPage }: Params) => {
  const [searchParams, setSearchParams] = useSearchParams({
    page: initialPage,
  });

  const page = Number(searchParams.get("page"));

  const setPage = (page: number) => {
    setSearchParams({ page: page.toString() });
  };

  return { page, setPage };
};

export default useURLSearchParams;
