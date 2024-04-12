import logOut from "../functions/logOut"
import { URL_APP } from "../URLS/URL";
import Swal from "sweetalert2";
export const updateEmail =  () => {
	try{
		Swal.fire({
			title: "Si cambias tu correo electrónico, deberá́s iniciar sesión de nuevo",
			icon: "warning"
		}).then(()=>{
			logOut();
			sessionStorage.clear()
			location.replace(URL_APP);
		})

	}catch(error){
		console.log(error)
	}
}