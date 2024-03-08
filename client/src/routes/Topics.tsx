import TopicList from "../components/TopicList";
import CreateTopic from "../components/CreateTopic.tsx";
import useTopics from "../hooks/useTopics";
import useCurrentUser from "../hooks/useCurrentUser.ts";

function Topics() {
  const { topics, isPending, error } = useTopics();
  const { currentUser } = useCurrentUser();

  return (
    <section className="flex flex-col flex-1 p-12 max-h-screen justify-between">
      <div className="flex flex-col flex-1 overflow-auto">
        <TopicList topics={topics} isPending={isPending} error={error} showActions={currentUser?.isAdmin} />
      </div>
      <CreateTopic />
    </section>
  );
}

export default Topics;