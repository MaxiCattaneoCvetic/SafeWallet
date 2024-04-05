import React, { useEffect, useRef, useState } from "react";
import AccountBalance from "../../components/accountBalance/AccountBalance.jsx";
import CardAccount from "../../components/cardAccount/CardAccount.jsx";
import style from "./account.module.css";
import NavBar from "../../components/navBar/NavBar.jsx";
import upperCase from "../../functions/upperCase.js"
import { SpinnerDotted } from "spinners-react";
import Transactions from "../../components/transactions/Transactions.jsx";
const Account = () => {
  const isRun = useRef(false);
  const [vToken, setVToken] = useState();
  const [vUser, setVUser] = useState();
  const [countReload,setCountReload] = useState(0);

  useEffect(() => {
    setVToken(sessionStorage.getItem("token"));
    setVUser(JSON.parse(sessionStorage.getItem("user")));
  }, []);
  setTimeout(() => {
    if(countReload > 3)return
    if (!vUser && vToken) {
      location.reload();
      setCountReload(countReload + 1);
    }else if(vUser && vToken){
      setCountReload(0)
    }
  }, 1000);



  return (
    <>
      {vUser && vToken ? (
        <>
          <NavBar />
          <div className={style.backgroundAccount}>
            <div className={style.containerHello}>
              <h1 className={style.helloUser}>
                Hola {vUser === null ? "" : upperCase(vUser.name)}!!
              </h1>
              <h4>Bienvenido a Safe Wallet, es un gusto tenerte de vuelta 😃</h4>
            </div>
            <section className={style.balanceContainer}>
              <AccountBalance />
            </section>
            <section>
              <Transactions userId={vUser.id}/>
            </section>
          </div>
        </>
      ) : (
        <div className="spinner">
          <SpinnerDotted />
        </div>
      )}
    </>
  );
};

export default Account;
