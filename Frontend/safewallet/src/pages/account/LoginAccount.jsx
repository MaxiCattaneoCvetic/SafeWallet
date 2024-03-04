import Account from "./Account";
import { SpinnerDotted } from "spinners-react";
import useAuth from "../../security/UseAuth";


export default function LoginAccount() {
  const [isLogin] = useAuth();

  return isLogin ? <Account/> : <div className="spinner"><SpinnerDotted/></div> 
}
