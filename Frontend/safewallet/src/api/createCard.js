import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";

export default function createCard(idUser, dataCard){
	const config = {
		headers: {
			authorization: `Bearer ${sessionStorage.getItem("token")}`
		}
	}

	try{
		const response = axios.post(URL_GET_USER_CARD_BASE + idUser + "/cards", dataCard, config)
		return response
	}catch(error){
		return error
	}
}
	