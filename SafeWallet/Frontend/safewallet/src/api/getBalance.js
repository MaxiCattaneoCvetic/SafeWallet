import axios from "axios";
import {URL_GET_USER_BALANCE} from "../URLS/URL"



let token = sessionStorage.getItem("token");
const config = {
	headers: {
		authorization: `Bearer ${token}`,
	},
};


export const getBalance = async (email) => {
  try {
    const response = await axios.get(URL_GET_USER_BALANCE + email, config);
    return response.data;
  } catch (error) {
    console.log(error);
    throw error; // Re-lanzar el error para que pueda ser manejado en el contexto de la llamada a getBalance
  }
};
