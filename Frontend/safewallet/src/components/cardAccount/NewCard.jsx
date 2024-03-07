import { useState } from "react";
import style from "./newCard.module.css";

export default function NewCard() {
  const [contador, setContador] = useState(0);

  if (contador > 3) {
    setContador(3);
  } else if (contador < 0) {
    setContador(0);
  }

  function handleSubmit() {
    console.log("<zsd");
  }
  return (
    <>
      <form action="" className={style.form} method="post">
        {contador === 0 ? (
          <>
            <div>
              <label htmlFor="cardNumber">Número de Tarjeta:</label>
              <input className={style.inputCard} type="text" id="cardNumber" name="cardNumber" required />
            </div>
          </>
        ) : (
          ""
        )}
        {contador === 1 ? (
          <>
            <div>
              <label htmlFor="cardType">Tipo de Tarjeta:</label>
              <select className={style.inputCard} id="cardType" name="cardType" required>
                <option  value="">Seleccione...</option>
                <option  value="debito">Debito</option>
                <option  value="credito">Credito</option>
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
              />
            </div>
          </>
        ) : (
          ""
        )}

        {contador === 3 ? (
          <div>
            <label htmlFor="">CVV:</label>
            <input
              type="number"
              id="cvv"
              name="cvv"
              pattern="\d{3,4}"
              title="CVV debe ser de 3 o 4 dígitos"
              required
              className={`${style.inputCard} ${"sinflechas"}`}
            />
          </div>
        ) : (
          ""
        )}
        <div className={style.btnsContainer}>
          {contador === 3 ? (
            <>
              <div>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    setContador(contador - 1);
                  }}
                  className="scondbtn"
                >
                  volver atras
                </button>
              </div>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  handleSubmit();
                }}
                className="scondbtn"
              >
                Añadir Tarjeta
              </button>
            </>
          ) : (
            <>
              <div>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    setContador(contador - 1);
                  }}
                  className="scondbtn"
                >
                  volver atras
                </button>
              </div>
              <div>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    handleSubmit();
                    setContador(contador + 1);
                  }}
                  className="scondbtn"
                >
                  Siguiente
                </button>
              </div>
            </>
          )}
        </div>
      </form>
    </>
  );
}
