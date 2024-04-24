/* eslint-disable no-useless-catch */
import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";
const config = {
  headers: {
    authorization: `Bearer ${sessionStorage.getItem("token")}`,
  },
};

async function depositCard(id, cardNumber,amount) {
  let URL = URL_GET_USER_CARD_BASE + id + "/transferences";

	const data = {
		cardNumber: cardNumber,
		amount: amount
	}
	

  try {
    const response = await axios.post(URL, data, config);
    return response;
  } catch (error) {
    throw error;
  }
}

export default depositCard;
