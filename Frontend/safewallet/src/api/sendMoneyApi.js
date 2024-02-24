import axios from "axios";

async function sendMoneyApi(amount, cbuFrom, cbuTo) {
    const config = {
        headers: {
            authorization: `Bearer ${sessionStorage.getItem("token")}`,
        }
    }
    const toSend = {
        "cbuFrom": cbuFrom,
        "cbuTo": cbuTo,
        "amount": amount
    }

    try {
        const res = await axios.put("http://localhost:9090/transfer/send", toSend, config);

    } catch (error) {
        return error.response.data; 
    }
}

export default sendMoneyApi;
