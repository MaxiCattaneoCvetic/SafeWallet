import { useNavigate } from "react-router-dom";

function NoAuth() {
	const navigate = useNavigate();

	return(
		<>
		<button
		className="primarybtnMOBILE"
		onClick={(e) => {
			e.preventDefault();
			navigate("/account");
		}}
	>
		Ingresar
	</button>
	<button
		className="primarybtnMOBILE"
		onClick={() => {
			navigate("/register");
		}}
	>
		Registrarse
	</button>
	</>
	)

}

export default NoAuth