import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";
import Swal from "sweetalert2";

export default async function deleteCard(card, userId) {
  const URL = `${URL_GET_USER_CARD_BASE}${userId}/cards`;

  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
    data: {
      cardNumber: card.cardNumber,
    },
  };

  try {
    const response = await axios.delete(URL, config);
    return response;
  } catch (error) {
    Swal.fire({
      icon: "error",
      title: "Error",
      text: "No se pudo eliminar la tarjeta",
      footer: "Error: " + error.response.data,
    })
    throw error;
  }
}
