import { Dispatch } from "react";
import useCurrentUser from "../hooks/useCurrentUser.ts";

interface EditActionsProps {
  setEditModalOpen: Dispatch<boolean>;
  handleDelete: () => void;
}

function TopicActions({ setEditModalOpen, handleDelete }: EditActionsProps) {
  const { currentUser } = useCurrentUser();

  if (!currentUser?.isAdmin) {
    return null;
  }

  return (
    <div className="gap-3 top-0 right-1 absolute hidden group-hover:flex">
      <button className="bg-green-600 py-1 px-2 rounded-md" onClick={() => setEditModalOpen(true)}>Edit</button>
      <button className="bg-red-600 py-1 px-2 rounded-md" onClick={handleDelete}>Delete</button>
    </div>
  );
}

export default TopicActions;