import { Link, useLocation } from "react-router-dom";
import { formatDateString } from "../utils";
import { Message as MessageType, Topic } from "../types";
import Message from "./Message";

interface Props {
  messages: MessageType[];
}

function TopicMessages({ messages }: Props) {
  const { state } = useLocation();
  const topic = state as Topic;

  return (
    <div className="flex-1">
      <div className="flex gap-2 text-2xl font-bold">
        <Link className="text-black no-underline" to="/topics">
          Topics
        </Link>
        <span>&gt; </span>
        <Link className="text-black no-underline" to={`/topics/${topic.id}`} state={topic}>
          {topic.name}
        </Link>
      </div>
      <p>Created by {topic.user.username} {formatDateString(topic.createdAt)}</p>
      <div>
        {messages.map((message) => (
          <Message key={message.id} message={message} />
        ))}
      </div>
    </div>
  );
}

export default TopicMessages;
