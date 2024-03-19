import { useNavigate } from "react-router-dom";

interface Props {
  username: string;
}

function TopBar({ username }: Props) {
  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem("df-token");
    navigate("/login");
  };

  return (
    <header className="px-12 py-3 bg-gray-300 mb-3">
      <div className="flex flex-row justify-between">
        <h2>Logged in as {username}</h2>
        <button className="font-bold" onClick={handleLogout}>Log out</button>
      </div>
    </header>
  );

}

export default TopBar;