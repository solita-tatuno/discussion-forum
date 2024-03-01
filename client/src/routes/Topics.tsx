import TopicList from "../components/TopicList";
import useTopics from "../hooks/useTopics";

function Topics() {
  const { topics, isPending, error } = useTopics();

  return (
    <section className="flex flex-col flex-1 p-12">
      <TopicList topics={topics} isPending={isPending} error={error} />
    </section>
  );
}

export default Topics;