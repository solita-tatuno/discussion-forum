import { useState } from "react";
import TopicList from "../components/TopicList";
import CreateTopic from "../components/CreateTopic.tsx";
import useTopics from "../hooks/useTopics";
import Pagination from "../components/Pagination.tsx";


function Topics() {
  const limit = 9;
  const [page, setPage] = useState(0);
  const { data, isPending, error } = useTopics({ limit, page });

  return (
    <section className="flex flex-col flex-1 p-12 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <TopicList topics={data?.topics} isPending={isPending} error={error} />
      </div>
      <CreateTopic />
      <Pagination itemCount={data?.totalCount} page={page} setPage={setPage} limit={limit} />
    </section>
  );
}

export default Topics;