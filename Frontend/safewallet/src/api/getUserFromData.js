import axios from "axios";

async function getUserFromData(data){
	const URL = `http://localhost:9090/accounts/${data}/user`;
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