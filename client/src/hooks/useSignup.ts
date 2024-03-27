import { useNavigate } from "react-router-dom";
import { useMutation } from "@tanstack/react-query";
import { UserCredentials } from "../types";
import userService from "../services/users";
import { toast } from "react-toastify";

const useSignup = () => {
  const navigate = useNavigate();
  const { mutate } = useMutation({
    mutationFn: (credentials: UserCredentials) =>
      userService.createOne(credentials),
    onError: (error) => {
      toast.error(error.message);
    },
    onSuccess: () => {
      toast.success("Account created");
      navigate("/login");
    },
  });

  return { signup: mutate };
};

export default useSignup;
