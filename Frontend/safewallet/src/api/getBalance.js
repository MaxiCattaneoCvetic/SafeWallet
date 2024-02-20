import axios from "axios";
import {URL_GET_USER_BALANCE} from "../URLS/URL"



let token = sessionStorage.getItem("token");
const config = {
	headers: {
		authorization: `Bearer ${token}`,
	},
};


export const getBalance = (email) => {
  return axios.get(URL_GET_USER_BALANCE + email, config)
    .then((response) => {
      return response.data; // Devolver solo los datos de la respuesta
    })
    .catch((error) => {
      console.log(error);
      throw error; // Re-lanzar el error para que pueda ser manejado en el contexto de la llamada a getBalance
    });
};
