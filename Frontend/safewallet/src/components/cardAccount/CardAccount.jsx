import style from "./cardAccount.module.css";
import { useEffect, useState } from "react";
import Modal from "../modal/Modal.jsx";
import NewCard from "./createCard/NewCard.jsx";
import ActiveCards from "../activeCards/ActiveCards.jsx";
import CardsManagements from "../cardsManagement/CardsManagements.jsx";

export default function CardAccount() {
  const [user, setUser] = useState({});
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);
  
  useEffect(() => {
    setUser(JSON.parse(sessionStorage.getItem("user")));
  }, []);

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
              <button
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal2(true);
                }}
                className="scondbtn"
              >
                Ver tarjetas activas
              </button>
            </div>
            {isModal2 ? (
              <Modal
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal2(false);
                }}
                title="Administar mis tarjetas"
                // eslint-disable-next-line react/no-children-prop
                children={<CardsManagements setIsModal2={setIsModal2} />}
              />
            ) : (
              ""
            )}
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
                title="Agregar nueva tarjeta"
                // eslint-disable-next-line react/no-children-prop
                children={<NewCard userId={user.id} setIsModal={setIsModal} />}
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
