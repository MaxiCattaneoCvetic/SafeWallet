import { useState } from "react";
import style from "./newCard.module.css";
import createCard from "../../../api/createCard.js";
import Swal from "sweetalert2";
import { useNavigate } from "react-router-dom";
import Card from "../../Card/Card.jsx";

export default function NewCard(props) {
  const [contador, setContador] = useState(0);
  const [card, setCard] = useState({
    cardNumber: "",
    cardType: "",
    expirationDate: "",
    cvv: "",
    name: "",
  });
  const [error, setError] = useState(false);
  const [error2, setError2] = useState(false);
  const navigate = useNavigate();

  function handleChange(e) {
    if (e.target.name === "expirationDate") {
      let date = new Date();
      let expirationDate = new Date(e.target.value);
      if (expirationDate < date) {
        setError(true);
      } else {
        setError(false);
      }
    }
    if (e.target.name === "cvv") {
      if (e.target.value.length > 3) {
        setError(true);
      } else {
        setError(false);
      }
    }
    if (e.target.name === "cardNumber") {
      if (e.target.value.length > 16 || e.target.value.length < 16) {
        setError(true);
      } else {
        setError(false);
      }
    }
    if (e.target.name === "name") {
      if (e.target.value.length > 25 || e.target.value.length < 3) {
        setError(true);
      } else if(!isNaN(e.target.value)){
        setError2(true);
      }else{
        setError(false);
        setError2(false);
      }
    }

    setCard({
      ...card,
      [e.target.name]: e.target.value,
    });
  }

  function handleKeyDown(e) {
    if (e.key === "e" || e.key === "E") {
      e.preventDefault();
    }
  }

  if (contador > 4) {
    setContador(4);
  } else if (contador < 0) {
    setContador(0);
  }

  async function handleSubmit() {
    //Formateo del objeto ya que el back utiliza / en vez de -
    const expirationDate = new Date(card.expirationDate);
    const formattedExpirationDate = `${expirationDate.getFullYear()}/${String(
      expirationDate.getMonth() + 1
    ).padStart(2, "0")}/${String(expirationDate.getDate()).padStart(2, "0")}`;

    const formattedCard = {
      ...card,
      expirationDate: formattedExpirationDate,
    };

    

    

    if (
      formattedCard.cardNumber.length < 16 ||
      formattedCard.cvv.length < 3 ||
      formattedCard.name.length < 3 ||
      card.expirationDate == "" ||
      formattedCard.cardType.length < 2
    ) {
      Swal.fire({
        position: "top-end",
        icon: "error",
        title:
          "Todos los datos son obligatorios, por favor verifica e intenta de nuevo",
        showConfirmButton: false,
        timer: 1500,
      });
      props.setIsModal(false);
      return;
    }
    createCard(props.userId, formattedCard)
      .then((response) => {
        // Logica de éxito
        console.log("Respuesta exitosa con status: " + response.status);
        switch (response.status) {
          case 201:
            props.setIsModal(false);
            Swal.fire({
              position: "top-end",
              icon: "success",
              title: "Tarjeta agregada",
              showConfirmButton: false,
              timer: 1500,
            });
            break;
          default:
            break;
        }
      })
      .catch((error) => {
        if (error.response.status === 401) {
          props.setIsModal(false);
          Swal.fire({
            position: "top-end",
            icon: "error",
            title:
              "Hubo un problema con la sesión, por favor inicia nuevamente",
            showConfirmButton: false,
            timer: 1500,
          });
          navigate("/");
        }
        let message = "Error al intentar añadir la tarjeta";
        if (error.response && error.response.data) {
          message += ": " + error.response.data;
        }
        Swal.fire({
          position: "top-end",
          icon: "error",
          title: message,
          showConfirmButton: false,
          timer: 1500,
        });
      });
  }
  return (
    <>
      <form action="" className={style.form} method="post">
        <div className={style.containerCardInput}>
          <div>
            {contador === 0 ? (
              <>
                <div>
                  <label htmlFor="cardNumber">Número de Tarjeta:</label>
                  <input
                    className={`${style.inputCard} ${"sinflechas"}`}
                    type="number"
                    id="cardNumber"
                    name="cardNumber"
                    required
                    placeholder="Contiene 16 digitos"
                    onChange={(e) => handleChange(e)}
                    value={card.cardNumber}
                    onKeyDown={handleKeyDown}
                  />
                  <p className={style.errors}>
                    {error ? "Longitud incorrecta" : ""}
                  </p>
                </div>
              </>
            ) : (
              ""
            )}
            {contador === 1 ? (
              <>
                <div>
                  <label htmlFor="cardType">Tipo de Tarjeta:</label>
                  <select
                    className={style.inputCard}
                    id="cardType"
                    name="cardType"
                    required
                    value={card.cardType}
                    onChange={(e) => handleChange(e)}
                  >
                    <option value="">Seleccione...</option>
                    <option value="debito">Debito</option>
                    <option value="credito">Credito</option>
                  </select>
                </div>
              </>
            ) : (
              ""
            )}
            {contador === 2 ? (
              <>
                <div>
                  <label htmlFor="">Fecha de Vencimiento:</label>
                  <input
                    type="date"
                    id="expirationDate"
                    name="expirationDate"
                    required
                    className={style.inputCard}
                    value={card.expirationDate}
                    onChange={(e) => handleChange(e)}
                  />
                </div>
                <p className={style.errors}>
                  {error ? "Tu tarjeta ya expiro" : ""}
                </p>
              </>
            ) : (
              ""
            )}
            {contador === 3 ? (
              <>
                <div>
                  <label htmlFor="">Nombre:</label>
                  <input
                    type="text"
                    id="name"
                    name="name"
                    required
                    className={style.inputCard}
                    pattern="[A-Za-záéíóúÁÉÍÓÚ\s]"
                    title="Por favor, introduce solo texto"
                    value={card.name}
                    onChange={(e) => handleChange(e)}
                  />
                  <p className={style.errors}>
                    {error ? "Longitud incorrecta" : ""}
                    <br></br>
                    {error2 ? "Nombre invalido" : ""}
                  </p>
                </div>
              </>
            ) : (
              ""
            )}
            {contador === 4 ? (
              <div>
                <label htmlFor="">CVV:</label>
                <input
                  type="number"
                  id="cvv"
                  name="cvv"
                  required
                  className={`${style.inputCard} ${"sinflechas"}`}
                  value={card.cvv}
                  maxLength={3}
                  onChange={(e) => handleChange(e)}
                  onKeyDown={handleKeyDown}
                />
                <p className={style.errors}>
                  {error ? "El CVV debe ser de 3 digitos" : ""}
                </p>
              </div>
            ) : (
              ""
            )}
          </div>
          <div>
            {contador === 4 ? (
              <div className={style.card}>
                <div className={style.cardBack}>
                  <div className={style.magneticStripe}></div>
                  <div className={style.signature} style={{ color: "black" }}>
                    {card.cardNumber}
                  </div>
                  <div className={style.cardNumbers}>
                    <p>{card.cvv.length > 1 ? card.cvv : "here"}</p>
                  </div>
                </div>
              </div>
            ) : (
              <Card
                cardNumber={card.cardNumber}
                cardType={card.cardType}
                expirationDate={card.expirationDate}
                name={card.name}
                cvv={card.cvv}
              ></Card>

              // <div className={style.card}>
              //   <div className={style.cardTextUp}>
              //     <p>~ Safe Wallet ~</p>
              //     <img
              //       src="/safewalletCard.svg"
              //       alt=""
              //       className={style.safewalletCard}
              //     />
              //   </div>
              //   <div>
              //     <p className={style.cardTextDown}>
              //       {cardNumbers(card.cardNumber)}
              //     </p>
              //   </div>
              //   <div className={style.cardDate}>
              //     <p>{card.expirationDate}</p>
              //     <p>{card.cardType}</p>
              //   </div>
              //   <div>
              //     <p className={style.cardName}>{card.name}</p>
              //   </div>
              // </div>
            )}
          </div>
        </div>
        <div className={style.btnsContainer}>
          {contador <= 3 && !error ? (
            <div style={{ display: "flex", gap: "0.5rem" }}>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  setContador(contador - 1);
                }}
                className="scondbtn"
              >
                Volver{" "}
              </button>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  setContador(contador + 1);
                }}
                className="scondbtn"
              >
                Siguiente
              </button>
            </div>
          ) : (
            ""
          )}
          {contador === 4 && !error ? (
            <div>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  setContador(0);
                }}
                className="scondbtn"
              >
                Volver al inicio
              </button>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  handleSubmit();
                }}
                className="scondbtn"
              >
                Añadir tarjeta
              </button>
            </div>
          ) : (
            ""
          )}
        </div>
      </form>
    </>
  );
}
