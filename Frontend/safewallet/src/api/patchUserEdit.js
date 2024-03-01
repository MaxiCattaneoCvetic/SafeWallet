import axios from "axios";
import { URL_EDIT_USER_ATRIBUTE } from "../URLS/URL";

const config = {
	headers: {
		authorization: `Bearer ${sessionStorage.getItem("token")}`,
	}
}


export default async function patchUserEdit(id, data) {
  try {
    const response = axios.patch(`${URL_EDIT_USER_ATRIBUTE}${id}`, data,config);
    return response;
  } catch (error) {
    return error;
  }
}
