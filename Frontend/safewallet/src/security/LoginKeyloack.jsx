import { useAuth  } from "../context/AuthProvider";

export default function LoginKeyloack() {
	const { login } = useAuth();



	const handleLoginClick = () => {
    login(); // Llama a la función login cuando el usuario haga clic en un botón u otro elemento
  };



  return (
    <div>
      <h1>Hello</h1>
      <button
        onClick={handleLoginClick}
				className="primarybtn"
      ></button>
			
    </div>
  );
}
