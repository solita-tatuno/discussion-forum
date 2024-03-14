import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics.ts";
import { PaginationValues } from "../types";

const useSingleTopic = (id: number, pagination: PaginationValues) => {
  const { data, isPending } = useQuery({
    queryKey: ["topic", id, pagination],
    queryFn: () => topicService.findOne(id, pagination),
  });

  return { topic: data, isPending };
};


export default useSingleTopic;