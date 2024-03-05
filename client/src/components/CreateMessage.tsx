import { useState } from "react";
import useCreateMessage from "../hooks/useCreateMessage";


interface Props {
  topicId: number;
}

function CreateMessage({ topicId }: Props) {
  const [message, setMessage] = useState<string>("");
  const { createMessage, error } = useCreateMessage(topicId);

  const handleCreateMessage = (topicId: number) => {
    createMessage({ message, topicId });
    setMessage("");
  };

  return (
    <section className="flex gap-6 items-center">
      <textarea
        className="w-full h-20 border-2 rounded-md"
        placeholder="Create new message"
        value={message}
        onChange={e => setMessage(e.target.value)} />
      <button
        className="bg-green-600 p-2 rounded-md"
        onClick={() => handleCreateMessage(topicId)}
      >
        Create
      </button>
      {error && <p className="text-red-600">{error.message}</p>}
    </section>
  );
}

export default CreateMessage;