import { useState } from "react";
import useCreateMessage from "../hooks/useCreateMessage";

interface Props {
  topicId: number;
}

function CreateMessage({ topicId }: Props) {
  const [message, setMessage] = useState<string>("");
  const { createMessage } = useCreateMessage(topicId);

  const handleCreateMessage = (topicId: number) => {
    createMessage({ message, topicId });
    setMessage("");
  };

  return (
    <section className="flex items-center gap-6">
      <textarea
        className="h-20 w-full rounded-md border-2"
        placeholder="Create new message"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      />
      <button
        className="rounded-md bg-green-600 p-2"
        onClick={() => handleCreateMessage(topicId)}
      >
        Create
      </button>
    </section>
  );
}

export default CreateMessage;
