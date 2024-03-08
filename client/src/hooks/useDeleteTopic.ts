import { useMutation, useQueryClient } from "@tanstack/react-query";
import topicService from "../services/topics";

const useDeleteTopic = () => {
  const queryClient = useQueryClient();
  const { mutate } = useMutation({
    mutationFn: (id: number) => topicService.deleteOne(id),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
  });

  return { deleteTopic: mutate };
};

export default useDeleteTopic;
