import { Topic } from "../types";
import { formatDateString } from "../utils";

interface Props {
  topic: Topic;
}

function TopicDetails({ topic }: Props) {
  return (
    <div className="flex flex-col items-start justify-between gap-3 rounded-md border-2 p-6 hover:bg-gray-300 sm:flex-row">
      <div className="basis-1/4 break-all">
        <p className="font-bold">Topic</p>
        <p>{topic.name}</p>
      </div>
      <div className="basis-1/4">
        <p className="font-bold">Created by</p>
        <p>{topic.user.username}</p>
      </div>
      <div className="basis-1/4">
        <p className="font-bold">Message count</p>
        <p>{topic.messageCount}</p>
      </div>
      <div className="basis-1/4">
        <p className="font-bold">Last message</p>
        <p>{formatDateString(topic.lastMessageTime)}</p>
      </div>
    </div>
  );
}

export default TopicDetails;
