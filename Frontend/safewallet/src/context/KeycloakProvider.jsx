// import React, { createContext, useState, useEffect } from 'react';
// import Keycloak from "keycloak-js";
// import swal from "sweetalert";
// import { URL_APP } from "../URLS/URL.js";
// import getUserData from "../api/getUserData.js";
// import decoderToken from "../functions/decodedToken.js";

// const KeyContext = createContext();


// export const KeycloakProvider = ({ children }) => {
//   const [user, setUser] = useState({});
//   const [token, setToken] = useState(null);
//   const [isLogin, setLogin] = useState(false);
//   const [keyInit, setKeyInit] = useState(false);

//   const client = new Keycloak({
//     url: "http://localhost:8080",
//     realm: "safewallet",
//     clientId: "frontend_client",
//   });
  
//   useEffect(() => {
//     if (!keyInit) {
//       client
//         .init({
//           onLoad: "login-required",
//         })
//         .then((res) => {
//           setLogin(res);
//           setToken(client.token);
//           sessionStorage.setItem("token", client.token);
//           sessionStorage.setItem("isLogin", res);

//           //const data = decoderToken(client.token);
//           //getUserData(data.email);
//           setKeyInit(true); // Marca que la inicialización ya se realizó
//         })
//         .catch((error) => {
//           swal("¡Ops, algo anda mal!", "El servidor agotó el tiempo de espera, por favor contacta con un administrador. \n" + error, "error").then(() => location.replace(URL_APP));
//         });
//     }
//   }, []); // Este efecto se ejecutará solo cuando keyInit cambie

//   return (
//     <KeyContext.Provider value={{ user, setUser, isLogin, setLogin, token, setToken }}>
//       {children}
//     </KeyContext.Provider>
//   );
// };

// export default KeyContext;
