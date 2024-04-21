import axios from "axios";
import { URL_GATEWAY } from "../URLS/URL.js";
async function getUserFromData(data){
	const URL = `${URL_GATEWAY}/accounts/${data}/user`;
	const config = {
		headers: {
			authorization: `Bearer ${sessionStorage.getItem("token")}`,
		},
	}

		// eslint-disable-next-line no-useless-catch
		try{
			const response = await axios.get(URL,config);
			return response;
		}catch(error){
			throw error;
		}



}

export default getUserFromData
