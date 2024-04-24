import axios from "axios";
import { URL_GET_USER_CARD_BASE } from "../URLS/URL";
import Swal from "sweetalert2";

async function getAllCards(id) {
	const URL = URL_GET_USER_CARD_BASE + id + "/cards";
  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  };

  // eslint-disable-next-line no-useless-catch
  try {
    let response = await axios.get(URL,config);
    return response;
  } catch (error) {
    throw error;
  }
}
export default getAllCards;
