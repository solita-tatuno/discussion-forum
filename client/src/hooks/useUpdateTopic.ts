import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import topicService from "../services/topics";
import { TopicUpdate } from "../types";

const useUpdateTopic = () => {
  const queryClient = useQueryClient();
  const { mutate } = useMutation({
    mutationFn: (args: TopicUpdate) => topicService.updateOne(args),
    onSuccess: () => {
      toast.success("Topic updated");
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { updateTopic: mutate };
};

export default useUpdateTopic;