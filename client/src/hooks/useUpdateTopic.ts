import { useMutation, useQueryClient } from "@tanstack/react-query";
import topicService from "../services/topics";
import { TopicUpdate } from "../types";

const useUpdateTopic = () => {
  const queryClient = useQueryClient();
  const { mutate } = useMutation({
    mutationFn: (args: TopicUpdate) => topicService.updateOne(args),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
  });

  return { updateTopic: mutate };
};

export default useUpdateTopic;