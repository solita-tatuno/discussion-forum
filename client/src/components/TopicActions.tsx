import { useState } from "react";
import useCurrentUser from "../hooks/useCurrentUser.ts";
import Modal from "./Modal.tsx";
import useUpdateTopic from "../hooks/useUpdateTopic.ts";
import useDeleteTopic from "../hooks/useDeleteTopic.ts";
import { Topic } from "../types";

interface Props {
  topic: Topic;
}

function TopicActions({ topic }: Props) {
  const { currentUser } = useCurrentUser();
  const [editModalOpen, setEditModalOpen] = useState<boolean>(false);
  const [newTopicName, setNewTopicName] = useState<string>(topic.name);
  const { updateTopic } = useUpdateTopic();
  const { deleteTopic } = useDeleteTopic();

  if (!currentUser?.isAdmin) {
    return null;
  }

  const handleUpdateTopic = () => {
    updateTopic({ id: topic.id, name: newTopicName });
    setEditModalOpen(false);
  };

  const handleDelete = () => {
    const confirm = window.confirm(
      "Are you sure you want to delete this topic?",
    );

    if (!confirm) {
      return;
    }

    deleteTopic(topic.id);
  };

  const handleEditModalClose = () => {
    setEditModalOpen(false);
    setNewTopicName(topic.name);
  };

  return (
    <div className="absolute right-1 top-0 hidden gap-3 group-hover:flex">
      <button
        className="rounded-md bg-green-600 px-2 py-1"
        onClick={() => setEditModalOpen(true)}
      >
        Edit
      </button>
      <button
        className="rounded-md bg-red-600 px-2 py-1"
        onClick={handleDelete}
      >
        Delete
      </button>

      <Modal isOpen={editModalOpen} onClose={handleEditModalClose}>
        <div className="flex flex-col gap-3 text-center">
          <h2 className="text-2xl font-semibold">Update Topic</h2>
          <input
            type="text"
            placeholder="New topic name"
            value={newTopicName}
            onChange={(e) => setNewTopicName(e.target.value)}
          />
          <button
            className="rounded-md bg-green-600 p-3"
            onClick={handleUpdateTopic}
          >
            Update
          </button>
        </div>
      </Modal>
    </div>
  );
}

export default TopicActions;
