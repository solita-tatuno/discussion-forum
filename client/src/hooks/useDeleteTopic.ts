import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import topicService from "../services/topics";

const useDeleteTopic = () => {
  const queryClient = useQueryClient();
  const { mutate } = useMutation({
    mutationFn: (id: number) => topicService.deleteOne(id),
    onSuccess: () => {
      toast.success("Topic deleted");
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { deleteTopic: mutate };
};

export default useDeleteTopic;
