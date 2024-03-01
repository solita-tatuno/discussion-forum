import { Topic } from "../types";


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
    <div className="flex flex-col flex-1">
      {error && <div>{error.message}</div>}
      <table className="text-left">
        <tbody>
        <tr className="border-b">
          <th>Topic</th>
          <th>Creator</th>
        </tr>
        {topics.map((topic) => (
          <tr className="border-b" key={topic.id}>
            <td className="py-6">{topic.name}</td>
            <td className="py-6">{topic.user.username}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default TopicList;