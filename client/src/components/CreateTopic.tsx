import { useState } from "react";
import useCreateTopic from "../hooks/useCreateTopic.ts";

function CreateTopic() {
  const [topicName, setTopicName] = useState<string>("");
  const { createTopic } = useCreateTopic();

  const handleCreateTopic = () => {
    createTopic(topicName);
    setTopicName("");
  };

  return (
    <section className="flex items-center gap-6">
      <input
        placeholder="Create new topic"
        value={topicName}
        onChange={(e) => setTopicName(e.target.value)}
      />
      <button
        className="rounded-md bg-green-600 p-2"
        onClick={handleCreateTopic}
      >
        Create
      </button>
    </section>
  );
}

export default CreateTopic;
