import { useMutation } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

import { UserCredentials } from "../types";
import loginService from "../services/login";

const useLogin = () => {
  const navigate = useNavigate();
  const { mutate, error } = useMutation({
    mutationFn: (credentials: UserCredentials) => loginService.login(credentials),
    onSuccess: (token) => {
      localStorage.setItem("df-token", JSON.stringify(token));
      navigate("/topics");
    },
  });

  return { login: mutate, error };
};

export default useLogin;