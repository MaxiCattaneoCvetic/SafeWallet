// URLS SISTEMA
const URL_IAM = "https://swmax.cloud/";
const URL_APP = "https://safewallet-sooty.vercel.app/";
const URL_GATEWAY = "https://gateway-production-b36f.up.railway.app";
const CLIENT_ID_KEYCLOAK = "safe-wallet-api";

// URLS CREAR USUARIOS
const REGISTER_USER_KEYCLOAK = `${URL_GATEWAY}/userKeycloak/create`;
const REGISTER_USER_FULL = `${URL_GATEWAY}/user`;


// URLS INFO USERS
const URL_GET_USER_ACCOUNT_LOGIN = `${URL_GATEWAY}/users/`; // Se utiliza para el login
const URL_GET_USER_BALANCE = `${URL_GATEWAY}/accounts/`;
const URL_GET_USER_CBU = `${URL_GATEWAY}/accounts/getcbu/`;


// URLS TRANSACTIONS  
const GET_TRANSACTION_BASE = `${URL_GATEWAY}/accounts/`;


// URLS CARDS
const URL_GET_USER_CARD_BASE = `${URL_GATEWAY}/accounts/`;




// URLS PATCH USERS INFO
const URL_EDIT_USER_ATRIBUTE = `${URL_GATEWAY}/users/`;


// URLS WELCOME-SAFEWALLET-GIFT
const URL_WELCOME_GIFT = `${URL_GATEWAY}/accounts/claimgift/`;

// URLS LOGOUT
const URL_LOGOUT = `${URL_GATEWAY}/userKeycloak/logout`;

// URLS SEND MONEY
const URL_SEND_MONEY = `${URL_GATEWAY}/accounts/send`;


// URL DOWNLOAD DOCUMENTS
const URL_DOWNLOAD_DOCUMENT = `${URL_GATEWAY}/download/pdf/generate`;

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
  URL_SEND_MONEY,
  URL_EDIT_USER_ATRIBUTE,
  URL_GET_USER_CARD_BASE,
  GET_TRANSACTION_BASE,
  URL_DOWNLOAD_DOCUMENT,
  URL_GATEWAY
};
