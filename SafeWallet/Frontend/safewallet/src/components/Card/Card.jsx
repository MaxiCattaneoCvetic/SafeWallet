import style from "./card.module.css";
import cardNumbers from "../../functions/cardNumbers";
import deleteCard from "../../api/deleteCard";
import Swal from "sweetalert2";
import depositCard from "../../api/depositCard";
import { useNavigate } from "react-router-dom";

function Card(props) {
  const navigate = useNavigate();
  function handleDelete(id) {
    const card = {
      cardNumber: id,
    };

    Swal.fire({
      title: "¿Estas seguro que deseas eliminar la tarjeta?",
      text: "al precionar aceptar la tarjeta se eliminara permanentemente de tu cuenta",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, quiero eliminarla",
    }).then((result) => {
      if (result.isConfirmed) {
        try {
          deleteCard(card, props.userId).then((response) => {
            if (response.status === 200) {
              Swal.fire({
                title: "¡Eliminada!",
                text: "Tu tarjeta ha sido eliminada.",
                icon: "success",
              }).then(() => props.setIsModal2(false));
            }
          });
        } catch (error) {
          console.log(error);
        }
      }
    });
  }

  async function handleDeposit(cardNumber) {
    const { value: amount } = await Swal.fire({
      title: "Ingresa el monto a depositar",
      input: "number",
      inputPlaceholder: "Ingresa el monto a depositar",
      inputAttributes: {
        min: 0,
        step: 0.01,
      },
    });
    if (amount) {
      try {
        const response = await depositCard(props.userId, cardNumber, amount);
        if (response.status === 201) {
          const result = await Swal.fire({
            title: "¡Depositado!",
            text: "Ya tienes disponible el nuevo saldo en tu cuenta.",
            icon: "success",
            showCancelButton: true,
            confirmButtonText: "Realizar otro depósito.",
            cancelButtonText: "Ver mi nuevo saldo.",
            cancelButtonColor: "#2BB32A"
          });
          if (!result.isConfirmed) {
            navigate("/account");
          }
        }
      } catch (error) {
        console.log(error);
      }
    }
  }

  return (
    <div key={props.id} className={style.card}>
      <div className={style.cardTextUp}>
        <p>~ Safe Wallet ~</p>
        <img
          src="/safewalletCard.svg"
          alt=""
          className={style.safewalletCard}
        />
      </div>
      <div>
        <p className={style.cardTextDown}>{cardNumbers(props.cardNumber)}</p>
      </div>
      <div className={style.cardDate}>
        <p>{props.expirationDate}</p>
        <p>{props.cardType}</p>
      </div>

      <div>
        {props.isButton ? (
          <>
            <div className={style.btnContainer}>
              <button
                className={style.btnDelete}
                onClick={() => handleDelete(props.cardNumber)}
              >
                Eliminar tarjeta
              </button>
              <button
                className={style.btnDelete}
                onClick={() => handleDeposit(props.cardNumber)}
              >
                Ingresar dinero
              </button>
            </div>
          </>
        ) : (
          ""
        )}
        <p className={style.cardName}>{props.name}</p>
      </div>
    </div>
  );
}

export default Card;
