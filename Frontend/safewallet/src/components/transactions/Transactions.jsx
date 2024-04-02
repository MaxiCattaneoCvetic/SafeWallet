import { useEffect, useState } from "react";
import style from "./transactions.module.css";
import { getTransactions } from "../../api/getTransactions";
import getTransactionDetail from "../../api/getTransactionDetail";
import { Modal } from "react-responsive-modal";
import TransactionDetail from "./TransactionDetail.jsx";
import "react-responsive-modal/styles.css";

function Transactions(props) {
  const [data, setData] = useState([]);
  const [detail, setDetail] = useState({});
  const [open, setOpen] = useState(false);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [resFull, setResFull] = useState({});
  const onCloseModal = () => setOpen(false);

  useEffect(() => {
    getTransactions(props.userId, page, size).then((response) => {
      // let orden = response.data.content.sort(
      //   (a, b) => new Date(b.date) - new Date(a.date)
      // );
      setData(response.data.content);
      setResFull(response.data);
    });
  }, [page]);

  async function handleClick(idTransaction, userId) {
    setOpen(true);
    try {
      const response = await getTransactionDetail(userId, idTransaction);
      console.log(response);
      setDetail(response.data);
    } catch (error) {
      console.log(error);
    }
  }

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
          <section className={style.transactionSection}>
            <div className={style.test}>
              <p style={{ textAlign: "center" }}>Pagina {resFull.number} /{resFull.totalPages -1}</p>
              {data.map((transaction) => (
                <div
                  key={transaction.id}
                  className={style.transactionsBackground}
                  title="Ver mas información sobre la transacción"
                  onClick={() => {
                    handleClick(transaction.id, props.userId);
                  }}
                >
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
                        <p style={{ color: "green", fontWeight: "bold" }}>
                          $ {transaction.amount}
                        </p>
                      ) : (
                        <p style={{ color: "red", fontWeight: "bold" }}>
                          $ {transaction.amount}
                        </p>
                      )}
                    </div>
                    <div className={style.columnW}>
                      <h5>Fecha</h5>
                      <p>
                        {
                          (transaction.date = new Date(
                            transaction.date
                          ).toLocaleDateString("en-US"))
                        }
                      </p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
            <div>
              <button
                onClick={() => {
                  if(page <= 0) {
                    setPage(0);
                    return
                  }
                  setPage(page - 1);
                }}
                className={style.btnPage}
              >
                Anterior pagina
              </button>
              <button
                onClick={() => {
                  if(page >= resFull.totalPages - 1) {
                    setPage(0);
                    return
                  }
                  setPage(page + 1);
                }}
                className={style.btnPage}
              >
                Siguiente pagina
              </button>
            </div>
          </section>
        </>
      )}

      <Modal
        center
        styles={{ modal: { borderRadius: "10px" } }}
        open={open}
        onClose={onCloseModal}
      >
        <TransactionDetail detail={detail} />
      </Modal>
    </div>
  );
}

export default Transactions;
