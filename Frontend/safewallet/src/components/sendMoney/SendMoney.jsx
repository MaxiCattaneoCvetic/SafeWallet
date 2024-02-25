import { useState } from "react";
import sendMoneyApi from "../../api/sendMoneyApi.js";
import style from "./sendMoney.module.css";
import swal from "sweetalert";
import getCbu from "../../api/getCbu.js";
export default function SendMoney(props) {
  const [cbuTo, setCbu] = useState();
  const [cbuFrom, setCbuFrom] = useState(props.cbuFrom);
  const [alias, setAlias] = useState();
  const [amount, setAmount] = useState();

  async function handleSubmit(e) {
    e.preventDefault();
    swal({
      title: "¿Estás seguro de enviar este monto?",
      text: `Enviando $${amount} a ${cbuTo} con cbu ${cbuFrom}`,
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then(async (willDelete) => {
      if (willDelete) {
        let res = await sendMoneyApi(amount, cbuFrom, cbuTo);
        if (res.status === 200) {
          swal("Dinero enviado", {
            icon: "success",
          });
        }else{
          swal("No se envio el dinero \n Error: ", res.response.data, );
          return
        }
      } else {
        swal("No se envio el dinero \n Error: Contacte con un administrador. ");
        return
      }
    });
  }

  return (
    <>
      <form action="" className={style.formInput}>
        <label htmlFor="">Ingrese el Cbu o el alias</label>
        <input
          type="text"
          onChange={(e) => {
            setCbu(e.target.value);
          }}
          placeholder="Cbu o alias"
        />
        <label htmlFor="">Ingrese el monto</label>
        <input
          type="number"
          onChange={(e) => {
            setAmount(e.target.value);
          }}
          placeholder="Ingrese el monto $"
        />
        <button type="submit" onClick={handleSubmit}>
          Enviar dinero
        </button>
      </form>
    </>
  );
}
