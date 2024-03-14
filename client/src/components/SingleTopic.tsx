import { Link } from "react-router-dom";
import { formatDateString } from "../utils";
import { SingleTopic as SingleTopicType } from "../types";
import MessageActions from "./MessageActions.tsx";

interface Props {
  topic: SingleTopicType;
}

function SingleTopic({ topic }: Props) {
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
      <p>Created by {topic.user.username} {formatDateString(topic.createdAt)}</p>
      <div>
        {topic.messages.map((message) => (
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
        ))}
      </div>
    </div>
  );
}

export default SingleTopic;
