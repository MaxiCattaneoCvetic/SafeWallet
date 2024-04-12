import  logOut  from "../functions/logOut.js";
import axios from "axios";
import { URL_GET_USER_ACCOUNT_LOGIN } from "../URLS/URL.js";
import swal from "sweetalert";

function getUserData(email) {
  return new Promise((resolve, reject) => {
    const token = sessionStorage.getItem("token");
    const config = {
      headers: {
        authorization: `Bearer ${token}`,
      },
    };
    axios
      .get(`${URL_GET_USER_ACCOUNT_LOGIN}${email}`, config)
      .then((res) => {
        sessionStorage.setItem("user", JSON.stringify(res.data));
        resolve(res.data);
        return res
      })
      .catch((err) => {
        swal("¡Ops, algo anda mal!", `${err}`, "error").then(() => {
          logOut(email);
          reject(err); // Rechaza con el error
        });
      });
  });
}

export default getUserData;
