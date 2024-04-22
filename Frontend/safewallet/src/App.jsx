import LoginAccount from "./pages/account/LoginAccount.jsx";
import CardAccountPage from "./pages/cardAccount/CardAccountPage.jsx";
import Home from "./pages/home/Home.jsx";
import ProfilePage from "./pages/profile/profilePage.jsx";
import Register from "./pages/register/Register.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Test from "./Test.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "/register",
    element: <Register />,
  },
  {
    path: "/profile",
    element: <ProfilePage />,
  },
  {
    path: "/account",
    element: <LoginAccount />,
  },
  {
    path: "/myCards",
    element: <CardAccountPage />,
  },
  {
    path: "/test",
    element: <Test></Test>
  }
]);


function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
