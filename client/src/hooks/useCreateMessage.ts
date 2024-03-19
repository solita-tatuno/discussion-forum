import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "react-toastify";
import messageService from "../services/messages";


interface NewMessage {
  message: string;
  topicId: number;
}

const useCreateMessage = (topicId: number) => {
  const queryClient = useQueryClient();

  const { mutate } = useMutation({
    mutationFn: (args: NewMessage) => messageService.create({
      ...args,
      upVotes: 0,
    }),
    onSuccess: () => {
      toast.success("Message created");
      return queryClient.invalidateQueries({ queryKey: ["topicMessages", topicId] });
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { createMessage: mutate };
};

export default useCreateMessage;