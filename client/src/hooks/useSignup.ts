import { useMutation } from "@tanstack/react-query";
import { UserCredentials } from "../types";
import userService from "../services/users";
import { toast } from "react-toastify";

const useSignup = () => {
  const { mutate } = useMutation({
    mutationFn: (credentials: UserCredentials) => userService.createOne(credentials),
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { signup: mutate };
};

export default useSignup;