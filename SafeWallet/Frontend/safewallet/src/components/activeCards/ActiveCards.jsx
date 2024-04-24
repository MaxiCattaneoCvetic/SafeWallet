import React, { useEffect, useState } from "react";
import getAllCards from "../../api/getAllCards";
import Card from "../../components/Card/Card.jsx";
import style from "./activeCards.module.css";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

function ActiveCards(props) {
  const [activeCards, setActiveCards] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const userIdFromStorage = sessionStorage.getItem("user");
  const navigate = useNavigate();
  let userIdJSON = null;

  if (userIdFromStorage) {
    userIdJSON = JSON.parse(userIdFromStorage).id;
  }

  useEffect(() => {
    if (userIdJSON !== null) {
      getAllCards(userIdJSON)
        .then((response) => {
          setActiveCards(response.data);
          if (response.status === 200) {
            setLoading(false);
          }
        })
        .catch((error) => {
          console.error("Error al obtener las tarjetas:", error);
          if (error.response.status === 401) {
            Swal.fire({
              icon: "error",
              title: "Error",
              text: "No tiene permisos para realizar esta acción, por favor vuelva a iniciar sesión.",
            }).then(() => {
              navigate("/");
            });
          }
          setLoading(false);
        });
    }
  }, [userIdJSON, navigate]);

  const goToNextCard = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === activeCards.length - 1 ? 0 : prevIndex + 1
    );
  };

  const goToPreviousCard = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? activeCards.length - 1 : prevIndex - 1
    );
  };

  return (
    <>
      <h1>Tarjetas Activas</h1>
      {loading ? (
        <p>Cargando tarjetas...⌛</p>
      ) : (
        <div className={style.carousel}>
          {activeCards.length > 0 ? (
            <div
              key={activeCards[currentIndex].id}
              className={style.containerCards}
            >
              <Card
                cardNumber={activeCards[currentIndex].cardNumber}
                cardType={activeCards[currentIndex].cardType}
                expirationDate={activeCards[currentIndex].expirationDate}
                name={activeCards[currentIndex].name}
                cvv={activeCards[currentIndex].cvv}
                id={activeCards[currentIndex].id}
                userId={userIdJSON}
                isButton={true}
                setIsModal2={props.setIsModal2}
              />
              <div className={style.btncontainer}>
              <button onClick={goToPreviousCard}>Anterior</button>
              <button onClick={goToNextCard}>Siguiente</button>
              </div>
              <h3>
                Tarjeta {currentIndex + 1} de {activeCards.length}
              </h3>
            </div>
          ) : (
            <p>No hay tarjetas activas.</p>
          )}
        </div>
      )}
    </>
  );
}

export default ActiveCards;
