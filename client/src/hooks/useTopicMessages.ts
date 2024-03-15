import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics";
import { PaginationValues } from "../types";

const useTopicMessages = (topicId: number, pagination: PaginationValues) => {
  const { data, isPending } = useQuery({
    queryKey: ["topicMessages", topicId, pagination],
    queryFn: () => topicService.getMessages(topicId, pagination),
  });

  return { data, isPending };
};

export default useTopicMessages;