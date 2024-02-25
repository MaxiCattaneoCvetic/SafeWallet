import axios from "axios";
import { URL_WELCOME_GIFT } from "../URLS/URL";
import swal from "sweetalert";

let token = sessionStorage.getItem("token");
const config = {
  headers: {
    authorization: `Bearer ${token}`,
  },
};

const getGift = async (cbu) => {
  console.log(config);
  try {
    await axios.post(URL_WELCOME_GIFT + cbu, {}, config).then((response) => {
      if (response.status === 200) {
        alert("Regalo enviado");
      }
    });
  } catch (e) {
    if (e.response.status === 406) {
			swal("Error en la solicitud", e.response.data, "warning");
		} else {
      swal("Error en la solicitud", "Hubo un problema en el servidor, intente mas tarde", "error");
		}
  }
};

export default getGift;
