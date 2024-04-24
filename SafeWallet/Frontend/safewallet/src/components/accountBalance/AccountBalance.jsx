import style from "./accountBalance.module.css";
import Modal from "../../components/modal/Modal.jsx";
import SendMoney from "../sendMoney/SendMoney.jsx";
import { useState, useRef, useEffect } from "react";
import { useClickOutside } from "primereact/hooks";
import { getBalance } from "../../api/getBalance.js";
import swal from "sweetalert";
import { useNavigate } from "react-router-dom";
import getGift from "../../api/getGift.js";
import "react-responsive-modal/styles.css";
import "react-toastify/dist/ReactToastify.css";
import { toast, ToastContainer } from "react-toastify";
import copy from "copy-text-to-clipboard";
// eslint-disable-next-line no-undef
import formatNum from "format-num";


export default function AccountBalance() {
  const [isModal, setIsModal] = useState(false);
  const [visible, setVisible] = useState(false);
  const cbuRef = useRef(null);
  const aliasRef = useRef(null);
  const cvuRef = useRef(null);
  const [userBalance, setUserBalance] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    let userName = JSON.parse(sessionStorage.getItem("user"));
    getBalance(userName.id)
      .then((balance) => {
        setUserBalance(balance);
      })
      .catch((error) => {
        if (error.response.status === 401) {
          swal(
            "No hay una sesión activa o no tienes permisos para realizar esta accion"
          ).then(() => {
            sessionStorage.clear();
            navigate("/");
          });
        }
      });
  }, []);

  useClickOutside(cbuRef, () => {
    setVisible(false);
  });

  useClickOutside(aliasRef, () => {
    setVisible(false);
  });

  useClickOutside(cvuRef, () => {
    setVisible(false);
  });

  function handleGift() {
    getGift(userBalance.cbu);
  }

  const copiarAlPortapapeles = (type) => {
    setVisible(false);
    switch (type) {
      case "cbu":
        copy(userBalance.cbu);
        break;
      case "alias":
        copy(userBalance.alias);
        break;
      case "cvu":
        copy(userBalance.cvu);
        break;
      default:
        break;
    }

    toast.success(type + " copiado al portapapeles", {
      position: "top-center",
      autoClose: 5000,
      hideProgressBar: true,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: 0,
      theme: "dark",
    });
  };

  return (
    <div>
      <ToastContainer />
      {userBalance !== null ? (
        <div className={style.mainContainerBalance}>
          <p className={style.textBalance}>Saldo disponible:</p>
          <h2 className={style.cashNumber}>
            $ {formatNum(userBalance.balance)}
          </h2>

          <div className={style.btnContainer}>
            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={(e) => {
                e.preventDefault();
                setIsModal(true);
              }}
            >
              Enviar dinero
            </button>
            {isModal ? (
              <Modal
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal(false);
                }}
                title="Enviar dinero"
                // eslint-disable-next-line react/no-children-prop
                children={<SendMoney cbuFrom={userBalance.cbu} />}
              />
            ) : (
              ""
            )}

            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={() => setVisible(true)}
              label="Show"
            >
              Ver cbu
            </button>
            {visible === true ? (
              <div ref={cbuRef} className={style.showText}>
                <p>{userBalance.cbu}</p>
                <button
                  className="scondbtn"
                  onClick={() => copiarAlPortapapeles("cbu")}
                >
                  Copiar
                </button>
              </div>
            ) : null}

            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={() => setVisible(1)}
              label="Show"
            >
              Ver Alias
            </button>
            {visible === 1 ? (
              <div ref={aliasRef} className={style.showText}>
                <p>{userBalance.alias}</p>
                <button
                  className="scondbtn"
                  onClick={() => copiarAlPortapapeles("alias")}
                >
                  Copiar
                </button>
              </div>
            ) : null}

            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={() => setVisible(2)}
              label="Show"
            >
              Ver cvu
            </button>
            {visible === 2 ? (
              <div ref={cvuRef} className={style.showText}>
                <p>{userBalance.cvu}</p>
                <button
                  className="scondbtn"
                  onClick={() => copiarAlPortapapeles("cvu")}
                >
                  Copiar
                </button>
              </div>
            ) : null}

            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={handleGift}
            >
              Reclamar premio Safe Wallet
            </button>
            <button
              className={`scondbtn ${style.gridItem}`}
              onClick={() => navigate("/myCards")}
            >
              Mis tarjetas
            </button>
          </div>
        </div>
      ) : (
        "Cargando cuenta..."
      )}
    </div>
  );
}
