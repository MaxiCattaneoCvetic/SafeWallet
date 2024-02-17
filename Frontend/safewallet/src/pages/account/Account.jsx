import React, { useState, useEffect, useRef } from "react";
import AccountBalance from "../../components/accountBalance/AccountBalance.jsx";
import CardAccount from "../../components/cardAccount/CardAccount.jsx";
import style from "./account.module.css";
import NavBar from "../../components/navBar/NavBar.jsx";
import decoderToken from "../../functions/decodedToken.js";
import {URL_GET_USER_ACCOUNT} from "../../URLS/URL.js"
import axios from "axios";
const Account = ({ token }) => {
  const isRun = useRef(false);
  const [data, setData] = useState(null);


  useEffect(() => {
    if (isRun.current) return;

    isRun.current = true;

    if(token){
      const data = decoderToken(token)
      const config = {
        headers: {
          "authorization": `Bearer ${token}`,
        },
      };
      axios
        .get(`${URL_GET_USER_ACCOUNT}/${data.email}`,config)
        .then((res) => {
          console.log(res);
          setData(res.data)
        })
        .catch((err) => console.error(err));
    }
  }, []);




  return (
    <>
      {token ? (
        <>
          <NavBar user={data}/>
          <div className={style.backgroundAccount}>
            <div className={style.containerHello}>
              <h1 className={style.helloUser}>Hola {data === null ? "" : data.name}!!</h1>
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
