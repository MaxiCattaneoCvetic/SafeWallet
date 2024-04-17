import { useEffect, useState, useRef } from "react";
import Keycloak from "keycloak-js";
import swal from "sweetalert";
import { URL_APP } from "../URLS/URL.js";
import getUserData from "../api/getUserData.js";
import decoderToken from "../functions/decodedToken.js";
import { useNavigate } from "react-router-dom";

const useAuth = () => {
  const isRun = useRef(false);
  const [token, setToken] = useState(null);
  const [isLogin, setLogin] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (isRun.current) return;
    isRun.current = true;

    const client = new Keycloak({
      url: "https://swmax.cloud/",
      realm: "safewallet",
      clientId: "frontend_client",
    });

    const handleTokenExpired = () => {
      // Limpiar sessionStorage
      sessionStorage.clear();
      localStorage.clear();
      location.replace("https://safewallet-sooty.vercel.app/");
      swal(
        "Sesión expirada",
        "Tu sesión ha expirado. Por favor, inicia sesión nuevamente.",
        "warning"
      );
    };

    client
      .init({
        onLoad: "login-required",
        checkLoginIframe: false,
        refreshToken: false,
      })
      .then((res) => {
        if (res) {
          setLogin(res);
          setToken(client.token);
          sessionStorage.setItem("token", client.token);
          sessionStorage.setItem("isLogin", res.toString());
          const data = decoderToken(client.token);
          getUserData(data.email);

          setInterval(() => {
            client
              .updateToken(30) // Reintentar si el token expira en los próximos 30 segundos
              .then((refreshed) => {
                if (refreshed) {
                  // El token fue exitosamente renovado
                  setToken(client.token);
                  sessionStorage.setItem("token", client.token);
                } else {
                  console.log("");
                }
              })
              .catch(() => {
                handleTokenExpired();
              });
          }, 60000);
        }
      })
      .catch((error) => {
        swal(
          "¡Ops, algo anda mal!",
          "El servidor agotó el tiempo de espera, por favor contacta con un administrador. \n" +
            error,
          "error"
        ).then(() => location.replace("https://safewallet-sooty.vercel.app/"));
      });
  }, [navigate]);

  return [isLogin, token];
};

export default useAuth;
