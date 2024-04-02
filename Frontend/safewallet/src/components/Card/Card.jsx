import style from "./card.module.css";
import cardNumbers from "../../functions/cardNumbers";
import deleteCard from "../../api/deleteCard";
import Swal from "sweetalert2";

function Card(props) {
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
  
  async function handleDeposit(id) {
    const { value: amount } = await Swal.fire({
      title: "Ingresa el monto a depositar",
      input: "number",
      inputPlaceholder: "Ingresa el monto a depositar",
      inputAttributes: {
        min: 0,
        step: 0.01
      }
    });
    if (amount) {
      Swal.fire(`Acabas de ingresar a tu cuenta: $${amount} \n Desde tu tarjeta terminada en **${id.substring(13, 16)}`);
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
