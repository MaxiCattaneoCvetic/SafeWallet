import axios from "axios";
import { URL_APP, URL_LOGOUT } from "../URLS/URL";
import decodedToken from "./decodedToken";

async function logOut() {
  let data = JSON.parse(sessionStorage.getItem("user"));
  const token = sessionStorage.getItem("token");
  let email = null;

  // Si por algun motivo no podemos acceder al usuario que tiene la sesion iniciada, lo sacamos del token. Ya que los servicios son distintos y puede funcionar uno y otro no.
  if (!data) {
    data = decodedToken(token);
    email = data.email;
  }

  email = data.email;

  const config = {
    headers: {
      authorization: `Bearer ${token}`,
    },
  };

  try {
    const response = await axios.post(`${URL_LOGOUT}/${email}`, config)
    if(response.status === 200) {
      sessionStorage.clear();
      localStorage.clear();
      window.location.replace("https://safewallet-sooty.vercel.app/");
    }
  } catch (e) {
    console.log(e);
  }
}

export default logOut