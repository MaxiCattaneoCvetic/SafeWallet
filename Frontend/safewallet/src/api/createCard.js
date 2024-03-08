/* eslint-disable no-useless-catch */
import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";

async function createCard(idUser, dataCard){
	const config = {
		headers: {
			authorization: `Bearer ${sessionStorage.getItem("token")}`
		}
	};

	try {
		const response = await axios.post(`${URL_GET_USER_CARD_BASE}${idUser}/cards`, dataCard, config);
		return response;
	} catch (error) {
		throw error;
	}
}

export default createCard;
