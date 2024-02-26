// URLS SISTEMA
const URL_IAM = "http://localhost:8080";
const URL_APP = "http://localhost:5173";
const URL_GATEWAY = "http://localhost:9090";
const CLIENT_ID_KEYCLOAK = "safe-wallet-api";

// URLS CREAR USUARIOS
const REGISTER_USER_KEYCLOAK = `${URL_GATEWAY}/userKeycloak/create`;
const REGISTER_USER_FULL = `${URL_GATEWAY}/user`;

// URLS INFO USERS
const URL_GET_USER_ACCOUNT_LOGIN = `${URL_GATEWAY}/useraccount/`; // Se utiliza para el login
const URL_GET_USER_BALANCE = `${URL_GATEWAY}/accounts/`;
const URL_GET_USER_CBU = `${URL_GATEWAY}/accounts/getcbu/`;


// URLS WELCOME-SAFEWALLET-GIFT
const URL_WELCOME_GIFT = `${URL_GATEWAY}/accounts/claimgift/`;

// URLS LOGOUT
const URL_LOGOUT = `${URL_GATEWAY}/userKeycloak/logout`;

// URLS SEND MONEY
const URL_SEND_MONEY = `${URL_GATEWAY}/accounts/send`;

// const APPLICATION_LOGIN = `${URL_GATEWAY}/myAccount/login`;

export {
  CLIENT_ID_KEYCLOAK,
  REGISTER_USER_KEYCLOAK,
  REGISTER_USER_FULL,
  URL_GET_USER_ACCOUNT_LOGIN,
  URL_APP,
  URL_LOGOUT,
  URL_GET_USER_BALANCE,
  URL_WELCOME_GIFT,
  URL_GET_USER_CBU,
  URL_SEND_MONEY
};
