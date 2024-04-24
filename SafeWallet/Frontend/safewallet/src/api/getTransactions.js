import axios from "axios";
import { URL_GET_USER_BALANCE } from "../URLS/URL";

async function getTransactions(id,page,size,filterNumber) {
  const URL = URL_GET_USER_BALANCE + id + "/transactions?page=" + page + "&size=" +size + "&filterNumber=" + filterNumber;

  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`, 
    },
  };

  // eslint-disable-next-line no-useless-catch
  try {
    let response = await axios.get(URL, config);
    return response;
  } catch (error) {
    throw error;
  }
}

export { getTransactions };
