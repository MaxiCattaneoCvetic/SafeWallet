import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Profile from "./profile.jsx";
import { logOut } from "../../functions/logOut.jsx";
import swal from "sweetalert";

export default function ProfilePage() {
  const navigate = useNavigate();
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    const checkLoginStatus = () => {
      const loggedIn = sessionStorage.getItem("isLogin") === "true";
      setIsLogin(loggedIn);
      if (!loggedIn) {
        swal({
          title: "¡Ops, algo anda mal!",
          text:
            "Parece que no hay ninguna sesión iniciada, por favor inicia sesión",
          icon: "error",
          button: "Aceptar"
        }).then(() => navigate("/account"));
      }
    };

    checkLoginStatus();
  }, [navigate]);





  return isLogin ? <Profile /> : "";
}
