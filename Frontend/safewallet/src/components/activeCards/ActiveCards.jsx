import { useEffect, useState } from "react";
import getAllCards from "../../api/getAllCards";
import Card from "../../components/Card/Card.jsx";
import style from "./activeCards.module.css";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";

function ActiveCards(props) {
  const [activeCards, setActiveCards] = useState([]);
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
              
              navigate("/")
            });
          }
          setLoading(false);
        });
    }
  }, [userIdJSON]);

  return (
    <>
      <h1>Tarjetas Activas</h1>
      {loading ? (
        <p>Cargando tarjetas...⌛</p>
      ) : (
        <div>
          {activeCards.length > 0 ? (
            activeCards.map((card) => (
              <div key={card.id} className={style.containerCards}>
                <Card
                  cardNumber={card.cardNumber}
                  cardType={card.cardType}
                  expirationDate={card.expirationDate}
                  name={card.name}
                  cvv={card.cvv}
                  id={card.id}
                  userId={userIdJSON}
                  isButton={true}
                  setIsModal2={props.setIsModal2}
                />
              </div>
            ))
          ) : (
            <p>No hay tarjetas activas.</p>
          )}
        </div>
      )}
    </>
  );
}

export default ActiveCards;
