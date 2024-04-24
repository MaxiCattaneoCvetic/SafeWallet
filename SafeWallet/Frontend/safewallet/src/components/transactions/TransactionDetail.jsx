import styles from "./transactions.module.css";

function TransactionDetail(props) {
  const concepto = props.detail.transferDetail;
  let mesagge = "";
  let detalle = "";

  switch (concepto) {
    case "TRANSFER":
      if (props.detail.amount > 0) {
        mesagge =
          "Has recibido $" + props.detail.amount + " de " + props.detail.from;
          detalle = "Transferencia de terceros"
        break;
      } else {
        mesagge =
          "Has transferido $" + props.detail.amount * -1 + " a " + props.detail.from;
          detalle = "Transferencia a otra cuenta SafeWallet"
        break;
      }
    case "SERVICEPAYMENT":
      mesagge = "Has gastado" + props.detail.amount + " en servicios";
      detalle = "Servicios"
      break;
      case "DEPOSITCARD":
        mesagge = "Has depositado $" + props.detail.amount + " desde tu tarjeta terminada en **" + props.detail.cardNumber.substring(13, 16);
        detalle = "Deposito en cuenta propia con tarjeta"
      break;
      case "GIFT":
        mesagge = "Bienvenido a SafeWallet. Has recibido $" + props.detail.amount + " como premio de bienvenida"
        detalle = "Premio de bienvenida SafeWallet"
      break;
    default:
      mesagge = "No hay detalles";
  }

  return (
    <div>
      {props.detail && (
        <div className={styles.transactionDetail}>
          <h2 className={styles.transactionDetailTitle}>
            Detalle de la transacción
          </h2>
          <strong><p className={styles.transactionDetailText}>{mesagge}.</p></strong> 
          <p className={styles.transactionDetailText}>
            {props.detail.date && 
              "Fecha: " + props.detail.date.substring(0, 10)+"."}
          </p>
          <p className={styles.transactionDetailText}>Detalle: {detalle}.</p>
        </div>
      )}
    </div>
  );
}

export default TransactionDetail;
