import { useMutation, useQueryClient } from "@tanstack/react-query";
import useCurrentUser from "./useCurrentUser";
import messageService from "../services/messages";


interface NewMessage {
  message: string;
  topicId: number;
}

const useCreateMessage = (topicId: number) => {
  const queryClient = useQueryClient();
  const { currentUser } = useCurrentUser();

  const { mutate, error } = useMutation({
    mutationFn: (args: NewMessage) => messageService.create({
      ...args,
      userId: currentUser!.id,
      upVotes: 0,
    }),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topic", topicId] });
    },
  });

  return { createMessage: mutate, error };
};

export default useCreateMessage;