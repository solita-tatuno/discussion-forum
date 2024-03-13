import { Topic } from "../types";
import TopicRow from "./TopicRow.tsx";


interface Props {
  topics: Topic[];
}

function TopicList({ topics }: Props) {
  return (
    <ul className="flex flex-col gap-3">
      {topics.map((topic) => (
        <TopicRow
          key={topic.id}
          topic={topic}
        />
      ))}
    </ul>
  );
}

export default TopicList;