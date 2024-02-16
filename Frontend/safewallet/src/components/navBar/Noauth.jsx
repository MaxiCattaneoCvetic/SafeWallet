function NoAuth() {

	return(
		<>
		<button
		className="primarybtnMOBILE"
		onClick={(e) => {
			e.preventDefault();
			navigate(APPLICATION_LOGIN, { replace: true });
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