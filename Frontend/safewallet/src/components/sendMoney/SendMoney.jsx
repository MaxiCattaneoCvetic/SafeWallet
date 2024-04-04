import { useState } from "react";
import sendMoneyApi from "../../api/sendMoneyApi.js";
import style from "./sendMoney.module.css";
import swal from "sweetalert";
import getCbu from "../../api/getCbu.js";
import getUserFromData from "../../api/getUserFromData.js";
export default function SendMoney(props) {
  const [cbuTo, setCbu] = useState();
  const [cbuFrom, setCbuFrom] = useState(props.cbuFrom);
  const [alias, setAlias] = useState();
  const [amount, setAmount] = useState();

  async function swalFunction(response) {
    swal({
      title: `¿Enviar $${amount} a ${response.data}?`,
      text: `Al aceptar enviará $${amount} a la cuenta de SafeWallet de  ${response.data}.`,
      icon: "warning",
      buttons: true,
      dangerMode: true,
    }).then(async (willDelete) => {
      if (willDelete) {
        let res = await sendMoneyApi(amount, cbuFrom, cbuTo);
        if (res.status === 200) {
          swal("Dinero enviado", {
            icon: "success",
          }).then(() => window.location.reload());
        } else {
          swal("No se envio el dinero \n Error: ", res.response.data);
          return;
        }
      } else {
        swal("Se canceló la operación, tu dinero esta a salvo", {
          icon: "warning",
        });
        return;
      }
    });
  }

  async function handleSubmit(e) {
    e.preventDefault();
    try {
      const response = await getUserFromData(cbuTo);
      if (response.status === 200) {
        swalFunction(response);
      }
    } catch (err) {
      swal("Error", {
        icon: "error",
        text: err.response.data,
      });
    }
  }

  return (
    <>
      <div className={style.formContainer}>
        <img src="/sendMoney.png" alt="sendMoneyIcon" />
        <form action="" className={style.formInput}>
          <label className={style.labelForm} htmlFor="">
            Ingrese el Cbu o el alias
          </label>
          <input
            type="text"
            onChange={(e) => {
              setCbu(e.target.value);
            }}
            placeholder="Cbu o alias"
          />
          <label className={style.labelForm} htmlFor="">
            Ingrese el monto
          </label>
          <input
            type="number"
            className="sinflechas"
            onChange={(e) => {
              setAmount(e.target.value);
            }}
            placeholder="Ingrese el monto $"
          />
          <button className="primarybtn" type="submit" onClick={handleSubmit}>
            Enviar dinero
          </button>
        </form>
      </div>
    </>
  );
}
