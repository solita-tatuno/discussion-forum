import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

import { UserCredentials } from "../types";
import loginService from "../services/login";
import { toast } from "react-toastify";

const useLogin = () => {
  const navigate = useNavigate();
  const { mutate } = useMutation({
    mutationFn: (credentials: UserCredentials) =>
      loginService.login(credentials),
    onSuccess: (token) => {
      localStorage.setItem("df-token", JSON.stringify(token));
      navigate("/topics");
    },
    onError: (error) => {
      toast.error(error.message);
    },
  });

  return { login: mutate };
};

export default useLogin;
