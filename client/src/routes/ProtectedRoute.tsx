import useCurrentUser from "../hooks/useCurrentUser.ts";
import { Navigate } from "react-router-dom";
import { ReactNode } from "react";
import TopBar from "../components/TopBar.tsx";

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

  return (
    <>
      <TopBar username={currentUser.username} />
      {children}
    </>
  );
};

export default ProtectedRoute;