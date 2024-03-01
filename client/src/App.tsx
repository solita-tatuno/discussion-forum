import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import Signup from "./routes/Signup.tsx";
import Login from "./routes/Login.tsx";
import Topics from "./routes/Topics.tsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Signup />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/topics",
    element: <Topics />,
  },
]);

function App() {
  return (
    <main className="min-h-screen flex flex-col">
      <RouterProvider router={router} />
    </main>
  );
}

export default App;

