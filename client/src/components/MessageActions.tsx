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
    updateMessage({ id: message.id, message: messageToEdit, userId: message.user.id, upVotes: message.upVotes });
    setIsModalOpen(false);
  };

  return (
    <div className="absolute right-2">
      <button className="bg-green-600 py-1 px-2 rounded-md" onClick={() => setIsModalOpen(true)}>Edit</button>

      <Modal isOpen={isModalOpen} onClose={modalOnClose}>
        <div className="flex flex-col gap-3 text-center">
          <h2 className="text-2xl font-bold">Edit Message</h2>
          <textarea
            className="w-full p-2 rounded-md border-2"
            defaultValue={messageToEdit}
            onChange={(e) => setMessageToEdit(e.target.value)}
          />
          <button className="bg-green-600 py-1 px-2 rounded-md" onClick={handleUpdateMessage}>Save</button>
        </div>
      </Modal>

    </div>
  );
}

export default MessageActions;