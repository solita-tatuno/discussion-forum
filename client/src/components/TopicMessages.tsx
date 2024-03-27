import { Link } from "react-router-dom";
import { formatDateString } from "../utils";
import { Message as MessageType, Topic } from "../types";
import Message from "./Message";

interface Props {
  messages: MessageType[];
  topic: Topic;
}

function TopicMessages({ messages, topic }: Props) {
  return (
    <div className="flex-1">
      <div className="flex gap-2 text-2xl font-bold">
        <Link className="text-black no-underline" to="/topics">
          Topics
        </Link>
        <span>&gt; </span>
        <Link className="text-black no-underline" to={`/topics/${topic.id}`}>
          {topic.name}
        </Link>
      </div>
      <p>
        Created by {topic.user.username} {formatDateString(topic.createdAt)}
      </p>
      <div>
        {messages.map((message) => (
          <Message key={message.id} message={message} />
        ))}
      </div>
    </div>
  );
}

export default TopicMessages;
