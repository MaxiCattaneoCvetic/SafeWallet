import axios from "axios";
import { URL_GET_USER_BALANCE } from "../URLS/URL";

async function getTransactions(id,page,size) {
  const URL = URL_GET_USER_BALANCE + id + "/activity?page=" + page + "&size=" +size;

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
