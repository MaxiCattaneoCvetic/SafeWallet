import axios from "axios";
import { URL_GET_USER_CBU } from "../URLS/URL";


let token = sessionStorage.getItem("token");
const config = {
  headers: {
    authorization: `Bearer ${token}`,
  },
};

const getcbu = async (cbu) => {
  console.log(config);
  try {
    await axios.get(URL_GET_USER_CBU + cbu, config).then((response) => {
			console.log(response);
    });
  } catch (e) {
		console.log(e);
  }
};

export default getcbu;