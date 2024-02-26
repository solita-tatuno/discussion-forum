import { useMutation } from "@tanstack/react-query";
import { UserCredentials } from "../types";
import userService from "../services/users";

const useSignup = () => {
  const { mutate, error } = useMutation({
    mutationFn: (credentials: UserCredentials) => userService.createOne(credentials),
  });

  return { signup: mutate, error };
};

export default useSignup;