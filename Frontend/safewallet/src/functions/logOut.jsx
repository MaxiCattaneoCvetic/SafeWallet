import axios from "axios";
import { URL_APP, URL_LOGOUT } from "../URLS/URL";
import decodedToken from "./decodedToken";

export const logOut = () => {
  let data = JSON.parse(sessionStorage.getItem("user"));
  const token = sessionStorage.getItem("token");
  let email = null

  // Si por algun motivo no podemos acceder al usuario que tiene la sesion iniciada, lo sacamos del token. Ya que los servicios son distintos y puede funcionar uno y otro no.
  if (!data) {
    data = decodedToken(token);
    email = data.email
  }
    
    email = data.email;

  const config = {
    headers: {
      authorization: `Bearer ${token}`,
    },
  };

  try {
    axios.post(`${URL_LOGOUT}/${email}`, config).then((res) => {
      if (res.status == 200) {
        sessionStorage.clear();
        location.replace(URL_APP);
      }
    });
  } catch (e) {
    console.log(e);
  }
};