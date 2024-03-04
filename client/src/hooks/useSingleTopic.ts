import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics.ts";

const useSingleTopic = (id: string) => {
  const { data, isPending, error } = useQuery({
    queryKey: ["topic", id],
    queryFn: () => topicService.findOne(id),
  });

  return { topic: data, isPending, error };
};


export default useSingleTopic;