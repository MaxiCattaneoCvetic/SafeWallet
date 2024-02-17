import React, { useState, useEffect, useRef } from "react";
import AccountBalance from "../../components/accountBalance/AccountBalance.jsx";
import CardAccount from "../../components/cardAccount/CardAccount.jsx";
import style from "./account.module.css";
import NavBar from "../../components/navBar/NavBar.jsx";
import decoderToken from "../../functions/decodedToken.js";

const Account = ({ token }) => {
  const isRun = useRef(false);
  const [data, setData] = useState(null);
  

  useEffect(() => {
    setData(decoderToken(token))
  },[token] );

  return (
    <>
      {token ? (
        <>
          <NavBar />
          <div className={style.backgroundAccount}>
            <div className={style.containerHello}>
              <h1 className={style.helloUser}>Hola {data === null ? console.log("no existe") : data.email}</h1>
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
        ""
      )}
    </>
  );
};

export default Account;
