import React, { useEffect, useRef, useState } from "react";
import AccountBalance from "../../components/accountBalance/AccountBalance.jsx";
import CardAccount from "../../components/cardAccount/CardAccount.jsx";
import style from "./account.module.css";
import NavBar from "../../components/navBar/NavBar.jsx";

import { SpinnerDotted } from "spinners-react";
const Account = () => {
  const isRun = useRef(false);
  const [vToken, setVToken] = useState();
  const [vUser, setVUser] = useState();

  useEffect(() => {
    setVToken(sessionStorage.getItem("token"));
    setVUser(JSON.parse(sessionStorage.getItem("user")));
  }, []);
  setTimeout(() => {
    if (!vUser && vToken) {
      location.reload();
    }
  }, 1000);

  function upperCase(name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

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
              <h4>Que gusto tenerte de vuelta 😃</h4>
            </div>
            <section className={style.balanceContainer}>
              <AccountBalance />
            </section>
            <section>
              <CardAccount />
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
