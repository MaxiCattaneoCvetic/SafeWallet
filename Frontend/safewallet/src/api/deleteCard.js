import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";


export default async function deleteCard(numberCard) {

	axios.delete(URL_GET_USER_CARD_BASE)

}

