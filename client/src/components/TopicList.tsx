import { Topic } from "../types";
import TopicRow from "./TopicRow.tsx";


interface Props {
  topics: Topic[] | undefined;
  isPending: boolean;
}

function TopicList({ topics, isPending }: Props) {

  if (isPending) {
    return <p>Loading...</p>;
  }

  if (!topics) {
    return <p>No topics found</p>;
  }

  return (
    <>
      <ul className="flex flex-col gap-3">
        {topics.map((topic) => (
          <TopicRow
            key={topic.id}
            topic={topic}
          />
        ))}
      </ul>
    </>
  );
}

export default TopicList;