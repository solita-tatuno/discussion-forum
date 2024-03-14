import { useParams } from "react-router-dom";
import SingleTopic from "../components/SingleTopic.tsx";
import useSingleTopic from "../hooks/useSingleTopic.ts";
import CreateMessage from "../components/CreateMessage.tsx";
import Pagination from "../components/Pagination.tsx";
import useURLSearchParams from "../hooks/useURLSearchParams.ts";

function Topic() {
  const { page, size, setPage } = useURLSearchParams({ initialPage: "1", initialSize: "5" });
  const { id } = useParams();
  const { topic, isPending } = useSingleTopic(Number(id), { page, size });

  if (isPending) {
    return <p>Loading...</p>;
  }

  if (!topic) {
    return <p>No topic found</p>;
  }

  return (
    <section className="flex flex-col flex-1 p-12 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <SingleTopic topic={topic} />
      </div>
      <div className="flex justify-between items-center gap-3 flex-wrap sm:flex-row flex-col">
        <CreateMessage topicId={Number(id)} />
        <Pagination itemCount={topic.messageCount} page={page} setPage={setPage} size={size} />
      </div>
    </section>
  );
}

export default Topic;
