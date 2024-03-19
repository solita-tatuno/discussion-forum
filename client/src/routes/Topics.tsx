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
    <section className="flex max-h-screen flex-1 flex-col justify-between px-12 py-3">
      <div className="flex flex-1 flex-col overflow-auto">
        <TopicList topics={data.topics} />
      </div>
      <div className="flex flex-col flex-wrap items-center justify-between gap-3 sm:flex-row">
        <CreateTopic />
        <Pagination
          itemCount={data.totalCount}
          page={page}
          setPage={setPage}
          size={size}
        />
      </div>
    </section>
  );
}

export default Topics;
