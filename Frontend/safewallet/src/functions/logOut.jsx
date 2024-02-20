import axios from "axios";
import {URL_APP,URL_LOGOUT} from "../URLS/URL";
export const logOut = () => {
  const data = JSON.parse(sessionStorage.getItem("user"));
  const token = sessionStorage.getItem("token");
  const email = data.email;

  const config = {
    headers: {
      authorization: `Bearer ${token}`,
    },
  };

  try {
    axios
      .post(`${URL_LOGOUT}/${email}`, config)
      .then((res) => {
				if(res.status == 200){
					sessionStorage.clear()
          location.replace(URL_APP)
        }
      });
  } catch (e) {
    console.log(e);
  }
};
