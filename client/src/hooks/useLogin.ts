import { useMutation } from "@tanstack/react-query";
import { UserCredentials } from "../types";
import loginService from "../services/login";

const useLogin = () => {
  const { mutate, error } = useMutation({
    mutationFn: (credentials: UserCredentials) => loginService.login(credentials),
    onSuccess: (token) => {
      localStorage.setItem("df-token", JSON.stringify(token));
    },
  });

  return { login: mutate, error };
};

export default useLogin;