import { useState } from "react";
import useCurrentUser from "../hooks/useCurrentUser.ts";
import Modal from "./Modal.tsx";
import { Message } from "../types";
import useUpdateMessage from "../hooks/useUpdateMessage.ts";

interface Props {
  message: Message;
}

function MessageActions({ message }: Props) {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [messageToEdit, setMessageToEdit] = useState(message.message);
  const { updateMessage } = useUpdateMessage();
  const { currentUser } = useCurrentUser();

  if (currentUser?.id !== message.user.id) {
    return null;
  }

  const modalOnClose = () => {
    setIsModalOpen(false);
    setMessageToEdit(message.message);
  };

  const handleUpdateMessage = () => {
    updateMessage({
      id: message.id,
      message: messageToEdit,
      userId: message.user.id,
      upVotes: message.upVotes,
    });
    setIsModalOpen(false);
  };

  return (
    <div className="absolute right-2">
      <button
        className="rounded-md bg-green-600 px-2 py-1"
        onClick={() => setIsModalOpen(true)}
      >
        Edit
      </button>

      <Modal isOpen={isModalOpen} onClose={modalOnClose}>
        <div className="flex flex-col gap-3 text-center">
          <h2 className="text-2xl font-bold">Edit Message</h2>
          <textarea
            className="w-full rounded-md border-2 p-2"
            defaultValue={messageToEdit}
            onChange={(e) => setMessageToEdit(e.target.value)}
          />
          <button
            className="rounded-md bg-green-600 px-2 py-1"
            onClick={handleUpdateMessage}
          >
            Save
          </button>
        </div>
      </Modal>
    </div>
  );
}

export default MessageActions;
