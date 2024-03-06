import { useMutation, useQueryClient } from "@tanstack/react-query";
import messageService from "../services/messages";


interface NewMessage {
  message: string;
  topicId: number;
}

const useCreateMessage = (topicId: number) => {
  const queryClient = useQueryClient();

  const { mutate, error } = useMutation({
    mutationFn: (args: NewMessage) => messageService.create({
      ...args,
      upVotes: 0,
    }),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topic", topicId] });
    },
  });

  return { createMessage: mutate, error };
};

export default useCreateMessage;