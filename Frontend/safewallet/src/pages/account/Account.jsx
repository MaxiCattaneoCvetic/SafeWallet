import React, { useState, useEffect, useRef, useContext } from "react";
import AccountBalance from "../../components/accountBalance/AccountBalance.jsx";
import CardAccount from "../../components/cardAccount/CardAccount.jsx";
import style from "./account.module.css";
import NavBar from "../../components/navBar/NavBar.jsx";
import decoderToken from "../../functions/decodedToken.js";
import { URL_GET_USER_ACCOUNT_LOGIN } from "../../URLS/URL.js";
import axios from "axios";
import swal from "sweetalert";
import { useNavigate } from "react-router-dom";
import { logOut } from "../../functions/logOut.jsx";
import { SpinnerDotted } from "spinners-react";
const Account = () => {
  const isRun = useRef(false);
  const [data, setData] = useState(null);
  const token = sessionStorage.getItem("token");
  const navigate = useNavigate();

  useEffect(() => {
    if (isRun.current) return;
    isRun.current = true;

    if (token) {
      const data = decoderToken(token);
      const config = {
        headers: {
          authorization: `Bearer ${token}`,
        },
      };
      axios
        .get(`${URL_GET_USER_ACCOUNT_LOGIN}${data.email}`, config)
        .then((res) => {
          setData(res.data);
          sessionStorage.setItem("user", JSON.stringify(res.data));
        })
        .catch((err) => {
          swal("¡Ops, algo anda mal!", `${err}`, "error").then(() => {
            logOut(data.email);
            navigate("/");
          });
        });
    }
  }, []);

  function upperCase(name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }

  return (
    <>
      {token && data !== null ? (
        <>
          <NavBar />
          <div className={style.backgroundAccount}>
            <div className={style.containerHello}>
              <h1 className={style.helloUser}>
                Hola {data === null ? "" : upperCase(data.name)}!!
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
          <SpinnerDotted
            size={74}
            thickness={180}
            speed={159}
            color="#2BB32A"
          />
        </div>
      )}
    </>
  );
};

export default Account;
