import { useParams } from "react-router-dom";
import SingleTopic from "../components/SingleTopic.tsx";
import useSingleTopic from "../hooks/useSingleTopic.ts";


function Topic() {
  const { id } = useParams();

  if (!id) {
    return <p>No topic found</p>;
  }

  const { topic, isPending} = useSingleTopic(id);

  return (
    <section className="flex flex-col flex-1 p-12">
      <SingleTopic topic={topic} isPending={isPending}/>
    </section>
  );
}

export default Topic;