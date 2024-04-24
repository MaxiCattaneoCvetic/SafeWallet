/* eslint-disable no-useless-catch */
import axios from "axios";
import { GET_TRANSACTION_BASE } from "../URLS/URL";

const config = {
  headers: {
    authorization: `Bearer ${sessionStorage.getItem("token")}`,
  },
};
async function getTransactionDetail(id, idTransaction) {
  const URL = GET_TRANSACTION_BASE + id + "/activity/" + idTransaction;

  try {
    const response = await axios.get(URL, config);
    return response;
  } catch (error) {
    throw error;
  }
}

export default getTransactionDetail;
