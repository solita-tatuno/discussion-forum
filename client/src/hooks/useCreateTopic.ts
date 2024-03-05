import { useMutation, useQueryClient } from "@tanstack/react-query";
import useCurrentUser from "./useCurrentUser.ts";
import topicService from "../services/topics.ts";

const useCreateTopic = () => {
  const queryClient = useQueryClient();
  // TODO: Check if currentUser is undefined
  const { currentUser } = useCurrentUser();


  const { mutate, error } = useMutation({
    mutationFn: (name: string) => topicService.create({ name, userId: currentUser!.id }),
    onSuccess: () => {
      return queryClient.invalidateQueries({ queryKey: ["topics"] });
    },
  });

  return { createTopic: mutate, error };
};

export default useCreateTopic;