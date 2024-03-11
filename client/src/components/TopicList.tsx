import { Topic } from "../types";
import TopicRow from "./TopicRow.tsx";


interface Props {
  topics: Topic[] | undefined;
  isPending: boolean;
  error: Error | null;
}

function TopicList({ topics, isPending, error }: Props) {

  if (isPending) {
    return <p>Loading...</p>;
  }

  if (!topics) {
    return <p>No topics found</p>;
  }

  return (
    <>
      {error && <div>{error.message}</div>}
      <table className="text-left">
        <tbody>
        <tr className="border-b">
          <th>Topic</th>
          <th>Creator</th>
        </tr>
        {topics.map((topic) => (
          <TopicRow
            key={topic.id}
            topic={topic}
          />
        ))}
        </tbody>
      </table>
    </>
  );
}

export default TopicList;