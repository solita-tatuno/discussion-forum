import { useSearchParams, useLocation, useNavigate } from "react-router-dom";

interface Params {
  initialPage: string;
}

const useURLSearchParams = ({ initialPage }: Params) => {
  const location = useLocation();
  const state = location.state;
  const navigate = useNavigate();
  const [searchParams, setSearchParams] = useSearchParams({ page: initialPage });

  const page = Number(searchParams.get("page"));

  const setPage = (page: number) => {
    setSearchParams({ page: page.toString() });
    navigate(`${location.pathname}?page=${page}`, { state: state });
  };

  return { page, setPage };
};

export default useURLSearchParams;