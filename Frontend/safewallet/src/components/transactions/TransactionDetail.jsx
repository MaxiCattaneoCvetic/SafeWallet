import styles from "./transactions.module.css";

function TransactionDetail(props) {
  const concepto = "transferencia";
  let mesagge = "";

  switch (concepto) {
    case "transferencia":
      if (props.detail.amount > 0) {
        mesagge =
          "Has recibido $" + props.detail.amount + " de " + props.detail.from;
        break;
      } else {
        mesagge =
          "Has transferido " + props.detail.amount + " a" + props.detail.to;
        break;
      }
    case "servicios":
      mesagge = "Has gastado" + props.amount + " en servicios";
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
          <p className={styles.transactionDetailText}>{mesagge}</p>
          <p className={styles.transactionDetailText}>
            {props.detail.date &&
              "Fecha: " + props.detail.date.substring(0, 10)}
          </p>
          <p className={styles.transactionDetailText}>Concepto: {concepto}</p>
        </div>
      )}
    </div>
  );
}

export default TransactionDetail;
