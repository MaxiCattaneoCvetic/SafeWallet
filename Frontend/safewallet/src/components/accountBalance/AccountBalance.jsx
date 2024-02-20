import style from "./accountBalance.module.css";
import Modal from "../../components/modal/Modal.jsx";
import { useState, useRef, useEffect } from "react";
import SendMoney from "../sendMoney/SendMoney.jsx";
import { useClickOutside } from "primereact/hooks";
import { getBalance } from "../../api/getBalance.js";

export default function AccountBalance() {
  const [isModal, setIsModal] = useState(false);
  const [visible, setVisible] = useState(false);
  const cbuRef = useRef(null);
  const aliasRef = useRef(null);
  const [userBalance, setUserBalance] = useState(null);

  useEffect(() => {
    let userName = JSON.parse(sessionStorage.getItem("user"));
    getBalance(userName.username)
      .then((balance) => {
        setUserBalance(balance);
      })
      .catch((error) => {
        console.error("Error al obtener el balance:", error);
      });
  }, []);

  useClickOutside(cbuRef, () => {
    setVisible(false);
  });

  useClickOutside(aliasRef, () => {
    setVisible(false);
  });

  return (
    <div>
      {userBalance !== null ? (
        <div className={style.mainContainerBalance}>
          <p className={style.textBalance}>Saldo disponible:</p>
          <h2 className={style.cashNumber}>${userBalance.balance}</h2>
          <div className={style.btnContainer}>
            <button
              className="scondbtn "
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
                children={<SendMoney />}
              />
            ) : (
              ""
            )}
            <button
              className="scondbtn"
              onClick={() => setVisible(true)}
              label="Show"
            >
              Ver CBU
            </button>
            {visible === true ? (
              <div ref={cbuRef} className={style.showText}>
                <p>CBU: {userBalance.cbu}</p>
                <button className="scondbtn">Copiar</button>
              </div>
            ) : null}
            <button
              className="scondbtn"
              onClick={() => setVisible(1)}
              label="Show"
            >
              Ver Alias
            </button>
            {visible === 1 ? (
              <div ref={aliasRef} className={style.showText}>
                <p>ALIAS: BLANCO.PEPE.BARCO</p>
                <button className="scondbtn">Copiar</button>
              </div>
            ) : null}
            <button className="scondbtn">Pagar servicios</button>
          </div>
        </div>
      ) : (
        "Cargando cuenta..."
      )}
    </div>
  );
}
