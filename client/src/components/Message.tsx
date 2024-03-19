import { Message as MessageType } from "../types";
import { formatDateString } from "../utils";
import MessageActions from "./MessageActions.tsx";

interface Props {
  message: MessageType;
}

function Message({ message }: Props) {
  return (
    <div key={message.id} className="flex mb-6 border-2 rounded-md relative">
      <div className="basis-1/4 bg-gray-300 p-6 text-center">
        <h3 className="border-b font-bold">{message.user.username}</h3>
      </div>
      <div className="flex flex-col gap-3 p-6 basis-3/4">
        <p className="border-b">{formatDateString(message.createdAt)}</p>
        <p>{message.message}</p>
      </div>
      <MessageActions message={message} />
    </div>
  );
}

export default Message;