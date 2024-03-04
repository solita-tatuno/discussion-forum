import useCurrentUser from "../hooks/useCurrentUser.ts";
import { Navigate } from "react-router-dom";
import { ReactNode } from "react";

interface Props {
  children: ReactNode;
}

const ProtectedRoute = ({ children }: Props) => {
  const { currentUser, isPending, isError } = useCurrentUser();

  if (isPending) {
    return <div>Loading...</div>;
  }

  if (!currentUser || isError) {
    return <Navigate to="/login" />;
  }

  return children;
};

export default ProtectedRoute;