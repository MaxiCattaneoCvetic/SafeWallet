import { useEffect, useState } from "react";
import style from "./transactions.module.css";
import { getTransactions } from "../../api/getTransactions";

const transactions = [
  {
    id: 1,
    name: "Maxi",
    amount: 10000,
    date: "10/10/2024",
  },
  {
    id: 2,
    name: "Laura",
    amount: 7500,
    date: "11/10/2024",
  },
  {
    id: 3,
    name: "Juan",
    amount: 6000,
    date: "12/10/2024",
  },
  {
    id: 4,
    name: "Ana",
    amount: 12000,
    date: "13/10/2024",
  },
  {
    id: 5,
    name: "Carlos",
    amount: 8500,
    date: "14/10/2024",
  },
  {
    id: 6,
    name: "María",
    amount: 9500,
    date: "15/10/2024",
  },
  {
    id: 7,
    name: "Pedro",
    amount: 11000,
    date: "16/10/2024",
  },
  {
    id: 8,
    name: "Sofía",
    amount: 8000,
    date: "17/10/2024",
  },
  {
    id: 9,
    name: "Luis",
    amount: 7000,
    date: "18/10/2024",
  },
  {
    id: 10,
    name: "Elena",
    amount: 10500,
    date: "19/10/2024",
  },
];

function Transactions(props) {
  const [data, setData] = useState([]);

  useEffect(() => {
    getTransactions(props.userId).then((response) => {
      setData(response.data);
    });
    console.log(data);
  }, []);

  return (
    <div className={style.transactionMainContainer}>
      <div>
        <h2 className="titles">Tu última actividad</h2>
      </div>
      {data.length === 0 ? (
        <div className={style.transactionsBackground}>
          <h3 className={style.noTransactions}>
            Parece que aún no has realizado ninguna transacción...🔎
          </h3>
        </div>
      ) : (
        <>
          {data.map((transaction) => (
            <div key={transaction.id} className={style.transactionsBackground}>
              <div className={style.transactionContainer}>
                <img
                  src="/safewallet-transaction.svg"
                  alt="safewallet transaction"
                  className={style.safewalletTransaction}
                />
                <div className={style.columnW}>
                  <h5>Nombre</h5>
                  <p>{transaction.from}</p>
                </div>
                <div className={style.columnW}>
                  <h5>Monto</h5>
                  {transaction.amount > 0 ? (
                    <p style={{ color: "green", fontWeight: "bold" }}>$ {transaction.amount}</p>
                  ) : (
                    <p style={{ color: "red", fontWeight: "bold" }}>$ {transaction.amount}</p>
                  )}
                </div>
                <div className={style.columnW}>
                  <h5>Fecha</h5>
                  <p>{transaction.date}</p>
                </div>
              </div>
            </div>
          ))}
        </>
      )}
    </div>
  );
}

export default Transactions;
