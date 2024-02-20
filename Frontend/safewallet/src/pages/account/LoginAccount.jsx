import Account from "./Account";
import useAuth from "../../security/UseAuth.jsx";
import { SpinnerDotted } from 'spinners-react';

export default function LoginAccount() {
	const [isLogin] = useAuth();
	return isLogin ? <Account/> : <div className="spinner"><SpinnerDotted size={74} thickness={180} speed={159} color="#2BB32A" /></div> 


}
