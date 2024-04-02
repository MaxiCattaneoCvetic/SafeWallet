import LoginAccount from "./pages/account/LoginAccount.jsx";
import CardAccountPage from "./pages/cardAccount/CardAccountPage.jsx";
import Home from "./pages/home/Home.jsx";
import ProfilePage from "./pages/profile/profilePage.jsx";
import Register from "./pages/register/Register.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

const router = createBrowserRouter([
  // {
  //   path: "/",
  //   element: <RootLayout />,
  //   //errorElement: <Error />,
  //   children: [
  //     {
  //       index: true,
  //       element: <Home />,
  //     },
  //   ],
  // },
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "register",
    element: <Register />,
  },
  {
    path: "profile",
    element: <ProfilePage />,
  },
  {
    path: "account",
    element: <LoginAccount></LoginAccount>,
  },
  {
    path: "myCards",
    element: <CardAccountPage></CardAccountPage>,
  },
]);

function App() {
  return (
    <>
      <RouterProvider router={router} />
    </>
  );
}

export default App;
