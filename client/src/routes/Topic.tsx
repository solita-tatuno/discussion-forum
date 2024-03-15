import { useParams } from "react-router-dom";
import SingleTopic from "../components/SingleTopic.tsx";
import CreateMessage from "../components/CreateMessage.tsx";
import Pagination from "../components/Pagination.tsx";
import useURLSearchParams from "../hooks/useURLSearchParams.ts";
import useTopicMessages from "../hooks/useTopicMessages.ts";

function Topic() {
  const size = 5;
  const { page, setPage } = useURLSearchParams({ initialPage: "1" });
  const { id } = useParams();

  const { data, isPending } = useTopicMessages(Number(id), { page, size });

  if (isPending) {
    return <p>Loading...</p>;
  }

  if (!data) {
    return <p>No topic found</p>;
  }

  return (
    <section className="flex flex-col flex-1 p-12 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <SingleTopic messages={data.messages} />
      </div>
      <div className="flex justify-between items-center gap-3 flex-wrap sm:flex-row flex-col">
        <CreateMessage topicId={Number(id)} />
        <Pagination itemCount={data.totalCount} page={page} setPage={setPage} size={size} />
      </div>
    </section>
  );
}

export default Topic;
