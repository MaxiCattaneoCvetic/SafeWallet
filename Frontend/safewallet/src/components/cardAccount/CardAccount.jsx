import style from "./cardAccount.module.css";
import createCard from "../../api/createCard.js";
import { useEffect, useState } from "react";
import Modal from "../modal/Modal.jsx";
import NewCard from "./NewCard.jsx";

export default function CardAccount() {
  const [user, setUser] = useState({});
  const [isModal, setIsModal] = useState(false);

  useEffect(() => {
    setUser(JSON.parse(sessionStorage.getItem("user")));
  }, []);
  function createNewCard() {
    createCard(user.id);
  }

  return (
    <>
      <h2 className="titles">Mis tarjetas:</h2>
      <div className={style.mainTargetContainer}>
        <div className={style.targetConteiner}>
          <div>
            <img src="/targets.png" alt="mis tarjetas" />
          </div>
          <div className={style.containercard}>
            <div className={style.buttonsContainer}>
              <button className="scondbtn">Ver tarjetas activas</button>
              <button className="scondbtn">Quitar tarjeta</button>
            </div>
            <button
              onClick={(e) => {
                e.preventDefault();
                setIsModal(true);
              }}
              className="scondbtn"
            >
              Agregar nueva tarjeta
            </button>


            {isModal ? (
              <Modal
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal(false);
                }}
                title="Agregar Nueva tarjeta"
                // eslint-disable-next-line react/no-children-prop
                children={<NewCard/>}
              />
            ) : (
              ""
            )}

          </div>
        </div>
      </div>
    </>
  );
}
