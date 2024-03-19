import { useParams } from "react-router-dom";
import TopicMessages from "../components/TopicMessages.tsx";
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
    <section className="flex max-h-screen flex-1 flex-col justify-between px-12 py-3">
      <div className="flex flex-1 flex-col overflow-auto">
        <TopicMessages messages={data.messages} />
      </div>
      <div className="flex flex-col flex-wrap items-center justify-between gap-3 sm:flex-row">
        <CreateMessage topicId={Number(id)} />
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

export default Topic;
