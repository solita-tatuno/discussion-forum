import { Link } from "react-router-dom";
import { Topic } from "../types";
import { useState } from "react";
import TopicActions from "./TopicActions.tsx";
import useUpdateTopic from "../hooks/useUpdateTopic.ts";
import Modal from "./Modal.tsx";
import useDeleteTopic from "../hooks/useDeleteTopic.ts";
import { formatDateString } from "../utils";

interface Props {
  topic: Topic;
}

function TopicRow({ topic }: Props) {
  const [editModalOpen, setEditModalOpen] = useState<boolean>(false);
  const [newTopicName, setNewTopicName] = useState<string>(topic.name);
  const { updateTopic } = useUpdateTopic();
  const { deleteTopic } = useDeleteTopic();

  const handleUpdateTopic = () => {
    updateTopic({ id: topic.id, name: newTopicName });
    setEditModalOpen(false);
  };

  const handleDelete = () => {
    const confirm = window.confirm("Are you sure you want to delete this topic?");

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
    <>
      <tr className="relative border-b hover:bg-gray-300 group">
        <td className="py-6">
          <Link className="no-underline text-black hover:text-blue-500" to={`/topics/${topic.id}`}>
            {topic.name}
          </Link>
          <TopicActions
            setEditModalOpen={setEditModalOpen}
            handleDelete={handleDelete}
          />
        </td>
        <td className="py-6">{topic.user.username}</td>
        <td className="py-6">{topic.messageCount}</td>
        <td className="py-6">{formatDateString(topic.lastMessageTime)}</td>
      </tr>

      <Modal isOpen={editModalOpen} onClose={handleEditModalClose}>
        <div className="flex flex-col gap-3 text-center">
          <h1 className="text-2xl font-semibold">Update Topic</h1>
          <input
            type="text"
            placeholder="New topic name"
            value={newTopicName}
            onChange={(e) => setNewTopicName(e.target.value)}
          />
          <button className="bg-green-600 rounded-md p-3" onClick={handleUpdateTopic}>Update</button>
        </div>
      </Modal>
    </>
  );
}


export default TopicRow;