import style from "./cardAccount.module.css";
import { useEffect, useState } from "react";
import Modal from "../modal/Modal.jsx";
import NewCard from "./createCard/NewCard.jsx";
import NavBar from "../../components/navBar/NavBar.jsx";
import CardsManagements from "../cardsManagement/CardsManagements.jsx";

export default function CardAccount(props) {
  const [isModal, setIsModal] = useState(false);
  const [isModal2, setIsModal2] = useState(false);


  return (
    <>
      <NavBar />
      <h2 className="titles">Mis tarjetas:</h2>
      <div className={style.mainCardContainer}>
        <div className={style.cardConteiner}>
          <div>
            <img src="/cards.png" alt="mis tarjetas" />
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
                Administrador de tarjetas
              </button>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal(true);
                }}
                className="scondbtn"
              >
                Añadir nueva tarjeta
              </button>
            </div>
            {isModal2 ? (
              <Modal
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal2(false);
                }}
                title="Administrador de tarjetas"
                // eslint-disable-next-line react/no-children-prop
                children={<CardsManagements setIsModal2={setIsModal2} />}
              />
            ) : (
              ""
            )}
            {isModal ? (
              <Modal
                onClick={(e) => {
                  e.preventDefault();
                  setIsModal(false);
                }}
                title="Añadir nueva tarjeta"
                // eslint-disable-next-line react/no-children-prop
                children={
                  <NewCard userId={props.user.id} setIsModal={setIsModal} />
                }
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
