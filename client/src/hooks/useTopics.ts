import { useQuery } from "@tanstack/react-query";
import topicService from "../services/topics";

const useTopics = () => {
  const { data, isPending, isError, error } = useQuery({
      queryKey: ["topics"],
      queryFn: () => topicService.getAll(),
    },
  );

  return { topics: data, isPending, isError, error };
};

export default useTopics;