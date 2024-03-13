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
    <section className="flex gap-6 items-center">
      <input
        placeholder="Create new topic"
        value={topicName}
        onChange={e => setTopicName(e.target.value)} />
      <button
        className="bg-green-600 p-2 rounded-md"
        onClick={handleCreateTopic}
      >
        Create
      </button>
    </section>
  );
}

export default CreateTopic;