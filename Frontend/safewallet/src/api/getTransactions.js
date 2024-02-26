import axios from "axios";
import { URL_GET_USER_BALANCE } from "../URLS/URL";

async function getTransactions(id) {
	const URL = URL_GET_USER_BALANCE + id + "/transactions"	
	
	const config = {
		headers: {
			authorization: `Bearer ${sessionStorage.getItem("token")}`,
		}
	}

	try{
		let response = await axios.get(URL,config)
		return response

	}catch(error){
		return error
	}

}

export { getTransactions };


