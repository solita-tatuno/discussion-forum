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
    <div className="absolute right-1 top-0 hidden gap-3 group-hover:flex">
      <button
        className="rounded-md bg-green-600 px-2 py-1"
        onClick={() => setEditModalOpen(true)}
      >
        Edit
      </button>
      <button
        className="rounded-md bg-red-600 px-2 py-1"
        onClick={handleDelete}
      >
        Delete
      </button>
    </div>
  );
}

export default TopicActions;
