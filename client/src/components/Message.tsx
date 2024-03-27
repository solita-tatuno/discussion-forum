import { Message as MessageType } from "../types";
import { formatDateString } from "../utils";
import MessageActions from "./MessageActions.tsx";

interface Props {
  message: MessageType;
}

function Message({ message }: Props) {
  return (
    <div key={message.id} className="relative mb-6 flex rounded-md border-2">
      <div className="basis-1/4 bg-gray-300 p-6 text-center">
        <h3 className="border-b font-bold">{message.user.username}</h3>
      </div>
      <div className="flex basis-3/4 flex-col gap-3 p-6">
        <p className="border-b">{formatDateString(message.createdAt)}</p>
        <p>{message.message}</p>
      </div>
      <MessageActions message={message} />
    </div>
  );
}

export default Message;
