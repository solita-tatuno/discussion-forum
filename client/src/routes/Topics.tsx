import { useSearchParams } from "react-router-dom";
import TopicList from "../components/TopicList";
import CreateTopic from "../components/CreateTopic.tsx";
import useTopics from "../hooks/useTopics";
import Pagination from "../components/Pagination.tsx";


function Topics() {
  const [searchParams, setSearchParams] = useSearchParams({ page: "1", limit: "6" });
  const page = Number(searchParams.get("page"));
  const limit = Number(searchParams.get("limit"));

  const { data, isPending } = useTopics({ limit, page });

  const setPage = (page: number) => {
    setSearchParams({ page: page.toString(), limit: limit.toString() });
  };

  return (
    <section className="flex flex-col flex-1 p-12 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <TopicList topics={data?.topics} isPending={isPending} />
      </div>
      <div className="flex justify-between items-center gap-3 flex-wrap sm:flex-row flex-col">
        <CreateTopic />
        <Pagination itemCount={data?.totalCount} page={page} setPage={setPage} limit={limit} />
      </div>
    </section>
  );
}

export default Topics;