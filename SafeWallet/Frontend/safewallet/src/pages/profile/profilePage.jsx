import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Profile from "./profile.jsx";
import useAuth from "../../security/UseAuth";
import { SpinnerDotted } from "spinners-react";


export default function ProfilePage() {
  const navigate = useNavigate();
  //const [isLogin, setIsLogin] = useState(false);
  const [user, setUser] = useState({});
  const [token, setToken] = useState(null);
  const [isLogin] = useAuth();

  useEffect(() => {
    const checkLoginStatus = () => {
      const isLogin = sessionStorage.getItem("isLogin");
      const token = sessionStorage.getItem("token");
      const user = sessionStorage.getItem("user");

      setUser(JSON.parse(user));
      setToken(token);
      
      
    };
    
    checkLoginStatus();
  }, [navigate]);

  // return isLogin && user && token ? <Profile user={user}  /> : "";


  return isLogin ? <Profile user={user} /> : <div className="spinner"><SpinnerDotted/></div>

}
