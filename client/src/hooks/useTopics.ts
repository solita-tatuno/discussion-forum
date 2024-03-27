import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics";
import { PaginationValues } from "../types";

const useTopics = (pagination: PaginationValues) => {
  const { data, isPending } = useQuery({
    queryKey: ["topics", pagination],
    queryFn: () => topicService.getAll(pagination),
  });

  return { data, isPending };
};

export default useTopics;
