import { useSearchParams } from "react-router-dom";

interface Params {
  initialPage: string;
  initialSize: string;
}

const useURLSearchParams = ({ initialPage, initialSize }: Params) => {
  const [searchParams, setSearchParams] = useSearchParams(
    { page: initialPage, size: initialSize });

  const page = Number(searchParams.get("page"));
  const size = Number(searchParams.get("size"));

  const setPage = (page: number) => {
    setSearchParams({ page: page.toString(), size: initialSize.toString() });
  };

  return { page, size, setPage };
};

export default useURLSearchParams;