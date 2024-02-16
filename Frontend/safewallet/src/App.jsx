import RootLayout from "./RootLayout.jsx";
import Account from "./pages/account/Account.jsx";
import Home from "./pages/home/Home.jsx";
import Profile from "./pages/profile/profile.jsx";
import Register from "./pages/register/Register.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import LoginKeyloack from "./security/LoginKeyloack.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    //errorElement: <Error />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "account",
        element: <Account></Account>,
      },
    ],
  },
  {
    path: "login",
    element: <LoginKeyloack />,
  },
  {
    path: "register",
    element: <Register />,
  },
  {
    path: "profile",
    element: <Profile />,
  },
]);

function App() {
  return (
    <>
      <RouterProvider router={router} />;
    </>
  );
}

export default App;
