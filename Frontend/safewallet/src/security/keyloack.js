// import Keycloak from "keycloak-js";
// // URI PARA EL CONTROL DE USUARIO: redirectUri: "http://localhost:8080/realms/safewallet/account/#/security/signingin",



// const keycloak = new Keycloak({
//   url: "http://localhost:8080",
//   realm: "safewallet",
//   clientId: "frontend_client",
// });

// const initKeycloak = () => {
//   keycloak.init({
//     onLoad: 'login-required',
//     silentCheckSsoRedirectUri: `${location.origin}/silent-check-sso.html`
// });
// };

// const getToken = () => {
//   console.log(keycloak.token);
// };

// const getTokenParsed = () => {
//   console.log(keycloak.tokenParsed);
// };

// const logOut = () => {
//   keycloak.logout
// };

// const login = () => {
 
// };

// const getUsername = () => {console.log(keycloak.tokenParsed?.preferred_username);}

// const UserService = {
//   initKeycloak,
//   getToken,
//   getTokenParsed,
//   logOut,
//   getUsername,
//   login
// };

// export default UserService;
