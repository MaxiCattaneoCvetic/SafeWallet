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
  try {
    await axios.post(URL_WELCOME_GIFT + cbu, {}, config).then((response) => {
      if (response.status === 200) {
        swal("Enviado!", response.data, "success").then(()=>location.reload())
      }
    });
  } catch (e) {
    if (e.response.status === 406) {
			swal("Error en la solicitud", e.response.data + " 😒", "warning");
		} else {
      swal("Error en la solicitud", "Hubo un problema en el servidor, intente mas tarde 😶‍🌫️  ", "error");
		}
  }
};

export default getGift;
