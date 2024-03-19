import TopicList from "../components/TopicList";
import CreateTopic from "../components/CreateTopic.tsx";
import useTopics from "../hooks/useTopics";
import Pagination from "../components/Pagination.tsx";
import useURLSearchParams from "../hooks/useURLSearchParams.ts";


function Topics() {
  const size = 7;
  const { page, setPage } = useURLSearchParams({ initialPage: "1" });
  const { data, isPending } = useTopics({ page, size });


  if (isPending) {
    return <p>Loading...</p>;
  }

  if (!data) {
    return <p>No topics found</p>;
  }

  return (
    <section className="flex flex-col flex-1 px-12 py-3 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <TopicList topics={data.topics} />
      </div>
      <div className="flex justify-between items-center gap-3 flex-wrap sm:flex-row flex-col">
        <CreateTopic />
        <Pagination itemCount={data.totalCount} page={page} setPage={setPage} size={size} />
      </div>
    </section>
  );
}

export default Topics;