import { useQuery } from "@tanstack/react-query";
import userService from "../services/users.ts";

const useCurrentUser = () => {
  const { data, isPending, isError } = useQuery({
      queryKey: ["currentUser"],
      queryFn: () => userService.getCurrentUser(),
    },
  );

  return { currentUser: data, isError, isPending };
};

export default useCurrentUser;