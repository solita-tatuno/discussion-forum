import { useParams } from "react-router-dom";
import SingleTopic from "../components/SingleTopic.tsx";
import useSingleTopic from "../hooks/useSingleTopic.ts";
import CreateMessage from "../components/CreateMessage.tsx";

function Topic() {
  const { id } = useParams();
  const { topic, isPending } = useSingleTopic(Number(id));

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
      <CreateMessage topicId={Number(id)} />
    </section>
  );
}

export default Topic;
