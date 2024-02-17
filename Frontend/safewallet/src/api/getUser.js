import axios from "axios";

const getUserFull = (email,config) => {

	try {
		const response = axios.get(`http://localhost:9090/accountFull/${email}`,config);
		return response;
	} catch (error) {
		console.log(error);
	}
}


const ApiUserInfo = {
	getUserFull
	
}

export default ApiUserInfo

