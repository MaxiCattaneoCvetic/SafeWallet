import style from "./accountBalance.module.css";

export default function AccountBalance() {
  return (
    <div className={style.mainContainerBalance}>
      <p className={style.textBalance}>Saldo disponible:</p>
      <h2 className={style.cashNumber}>$40.000</h2>
      <div className={style.btnContainer}>
        <button className="scondbtn ">Enviar dinero</button>
        <button className="scondbtn ">Ver CBU</button>
        <button className="scondbtn ">Ver ALIAS</button>
        <button className="scondbtn ">Pagar servicios</button>
      </div>
    </div>
  );
}
