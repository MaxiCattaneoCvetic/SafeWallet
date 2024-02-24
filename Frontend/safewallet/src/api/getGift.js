import axios from "axios";
import { URL_WELCOME_GIFT } from "../URLS/URL";


let token = sessionStorage.getItem("token");
const config = {
	headers: {
		authorization: `Bearer ${token}`,
	},
};

const getGift = async (cbu) => {
	console.log(config);
  try {
    await axios.post(URL_WELCOME_GIFT + cbu,{}, config)
    .then((response)=>{
			if(response.status === 200){
				alert("Regalo enviado")
			}
		})
  } catch (e) {
		if(e.response.status === 406){
			alert(e.response.data)
		}else{
			alert("Error al enviar el regalo")
		}
  }
};

export default getGift;
