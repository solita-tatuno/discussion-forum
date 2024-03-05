import { SingleTopic as SingleTopicType } from "../types";
import { Link } from "react-router-dom";

interface Props {
  topic: SingleTopicType | undefined;
  isPending: boolean;
}

function SingleTopic({ topic, isPending }: Props) {

  if (isPending) {
    return <p>Loading...</p>;
  }


  if (!topic) {
    return <p>No topic found</p>;
  }

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
      <p>Created by {topic.user.username} on XX.XX.XXXX</p>
      <div>
        {topic.messages.map((message) => (
          <div key={message.id} className="flex mb-6 border-2 rounded-md">
            <div className="basis-1/4 bg-gray-300 p-6 text-center">
              <h3 className="border-b font-bold">{message.user.username}</h3>
            </div>
            <div className="flex flex-col gap-3 p-6 basis-3/4">
              <p className="border-b">Posted on XX.XX.XXXX</p>
              <p>{message.message}</p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default SingleTopic;
