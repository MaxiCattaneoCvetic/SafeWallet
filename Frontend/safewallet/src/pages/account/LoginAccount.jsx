import Account from "./Account";
import useAuth from "../../security/UseAuth.jsx";
import Home from "../../pages/home/Home.jsx"

export default function LoginAccount() {
	const [isLogin, token] = useAuth();
	return isLogin ? <Account token={token}  /> : <Home />;


}
