import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import topicService from "../services/topics.ts";

const useCreateTopic = () => {
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: (name: string) => topicService.create(name),
    onSuccess: () => {
      toast.success("Topic created");
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
    onError: (error) => {
      toast.error(error.message);
    }
  });

  return { createTopic: mutate };
};

export default useCreateTopic;