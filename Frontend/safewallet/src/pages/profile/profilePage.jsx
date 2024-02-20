import useAuth from "../../security/UseAuth.jsx";
import Account from "../account/Account.jsx";
import Profile from "./profile.jsx";


function ProfilePage() {
  const [isLogin] = useAuth();

  return (
    <>
		{isLogin ? <Profile></Profile> : <Account />}
    </>
  );
}

export default ProfilePage;
