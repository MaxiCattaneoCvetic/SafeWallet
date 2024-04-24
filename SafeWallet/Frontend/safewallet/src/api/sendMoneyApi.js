import axios from "axios";
import { URL_SEND_MONEY } from "../URLS/URL";

async function sendMoneyApi(amount, cbuFrom, cbuTo) {
  const config = {
    headers: {
      authorization: `Bearer ${sessionStorage.getItem("token")}`,
    },
  };
  const toSend = {
    cbuFrom: cbuFrom,
    cbuTo: cbuTo,
    amount: amount,
  };

  try {
    const res = await axios.put(
      URL_SEND_MONEY,
      toSend,
      config
    );
    return res
  } catch (error) {
    return error
  }
}

export default sendMoneyApi;
