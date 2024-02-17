import { useEffect, useState, useRef } from "react";
import Keycloak from "keycloak-js";


const client = new Keycloak({
  url: "http://localhost:8080",
  realm: "safewallet",
  clientId: "frontend_client",
});

const useAuth = () => {
  const isRun = useRef(false);
  const [token, setToken] = useState(null);
  const [isLogin, setLogin] = useState(false);


  useEffect(() => {
    if (isRun.current) return;
    isRun.current = true;
    client
      .init({
        onLoad: "login-required",
      })
      .then((res) => {
        setLogin(res);
        setToken(client.token);
      });
  }, []);

  return [isLogin, token];
};

export default useAuth;