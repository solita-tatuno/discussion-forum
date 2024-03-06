import { useMutation, useQueryClient } from "@tanstack/react-query";
import topicService from "../services/topics.ts";

const useCreateTopic = () => {
  const queryClient = useQueryClient();

  const { mutate, error } = useMutation({
    mutationFn: (name: string) => topicService.create(name),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
  });

  return { createTopic: mutate, error };
};

export default useCreateTopic;