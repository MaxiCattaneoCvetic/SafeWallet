import RootLayout from "./RootLayout.jsx";
import { UserProvider } from "./context/AuthProvider.jsx";
import LoginAccount from "./pages/account/LoginAccount.jsx";
import Home from "./pages/home/Home.jsx";
import Profile from "./pages/profile/profile.jsx";
import Register from "./pages/register/Register.jsx";
import { createBrowserRouter, RouterProvider } from "react-router-dom";

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
        element: <LoginAccount></LoginAccount>,
      },
    ],
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
      <UserProvider>
        <RouterProvider router={router} />
      </UserProvider>
    </>
  )
}

export default App;
