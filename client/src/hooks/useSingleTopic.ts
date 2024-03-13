import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics.ts";

const useSingleTopic = (id: number) => {
  const { data, isPending } = useQuery({
    queryKey: ["topic", id],
    queryFn: () => topicService.findOne(id),
  });

  return { topic: data, isPending };
};


export default useSingleTopic;