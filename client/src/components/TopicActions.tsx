import useDeleteTopic from "../hooks/useDeleteTopic.ts";

interface Props {
  topicId: number;
}

function TopicActions({ topicId }: Props) {
  const { deleteTopic } = useDeleteTopic();

  const handleDelete = () => {
    const confirm = window.confirm("Are you sure you want to delete this topic?");

    if (!confirm) {
      return;
    }

    deleteTopic(topicId);
  };

  return (
    <div className="flex gap-3">
      <button className="bg-green-600 py-1 px-2 rounded-md">Edit</button>
      <button className="bg-red-600 py-1 px-2 rounded-md" onClick={handleDelete}>Delete</button>
    </div>
  );
}

export default TopicActions;