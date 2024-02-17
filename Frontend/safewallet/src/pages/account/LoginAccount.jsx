import Account from "./Account";
import useAuth from "../../security/UseAuth.jsx";
import NoLogin from "./NoLogin";

export default function LoginAccount() {
	const [isLogin, token] = useAuth();
	return isLogin ? <Account token={token}  /> : <NoLogin />;


}
