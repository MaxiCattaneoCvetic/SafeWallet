import { useEffect, useState, useRef } from "react";
import Keycloak from "keycloak-js";
import swal from "sweetalert";
import {URL_APP} from "../URLS/URL.js";



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
        sessionStorage.setItem("token", client.token);
        sessionStorage.setItem("isLogin", res);
      })
      .catch((error) => {
        swal("¡Ops, algo anda mal!", "El servidor agoto el tiempo de espera, por favor contacta con un administrador. \n" + error, "error").then(()=>location.replace(URL_APP))
      });
  }, []);

  return [isLogin, token];
};

export default useAuth;