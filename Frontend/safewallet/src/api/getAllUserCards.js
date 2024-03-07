import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";



export async function getAllUserCards(id) {

	const config = {
		headers: {
			authorization: `Bearer ${sessionStorage.getItem("token")}`
		}
	}

	try{
		const response = await axios.get(URL_GET_USER_CARD_BASE + id + "/cards",config)
		return response
	}catch(error){
		return error
	}
}

