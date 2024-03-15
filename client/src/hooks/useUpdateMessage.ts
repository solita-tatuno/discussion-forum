import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import messageService from "../services/messages";
import { MessageUpdate } from "../types";

const useUpdateMessage = () => {
  const queryClient = useQueryClient();
  const { mutate } = useMutation({
    mutationFn: (payload: MessageUpdate) => messageService.updateOne(payload),
    onSuccess: (data) => {
      toast.success("Message updated");
      return queryClient.invalidateQueries({ queryKey: ["topicMessages", data.topicId] });
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { updateMessage: mutate };
};

export default useUpdateMessage;