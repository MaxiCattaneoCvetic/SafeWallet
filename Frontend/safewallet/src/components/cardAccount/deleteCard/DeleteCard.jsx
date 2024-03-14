import Swal from "sweetalert2";
import deleteCard from "../../../api/deleteCard";
import style from "./deletecard.module.css";
import { useState } from "react";


function DeleteCard() {
  const [numberCard, setNumberCard] = useState();
  return (
    <>
      <form action="">
        <input
          type="number"
          className={`${style.asd} sinflechas`}
          placeholder="Ingresa el numero de tu tarjeta a eliminar"
          onChange={(e) => setNumberCard(e.target.value)}
          value={numberCard}
        />
        <button
          className="primarybtn"
          onClick={(e) =>{
            e.preventDefault();
            handleDelete(numberCard)
          }}
        >
          Eliminar tarjeta
        </button>
      </form>
    </>
  );
}

export default DeleteCard;
