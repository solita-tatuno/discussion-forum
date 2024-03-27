import { Link } from "react-router-dom";
import { Topic } from "../types";
import TopicActions from "./TopicActions.tsx";
import TopicDetails from "./TopicDetails.tsx";

interface Props {
  topic: Topic;
}

function TopicRow({ topic }: Props) {
  return (
    <li className="group relative">
      <Link className="text-black no-underline" to={`/topics/${topic.id}`}>
        <TopicDetails topic={topic} />
      </Link>

      <TopicActions topic={topic} />
    </li>
  );
}

export default TopicRow;
