import { useEffect, useState } from "react";
import style from "./transactions.module.css";
import { getTransactions } from "../../api/getTransactions";
import getTransactionDetail from "../../api/getTransactionDetail";
import { Modal } from "react-responsive-modal";
import TransactionDetail from "./TransactionDetail.jsx";
import "react-responsive-modal/styles.css";
import 'react-toastify/dist/ReactToastify.css';
import { toast, ToastContainer,Bounce } from 'react-toastify';
import DownloadReceipt from "../downloadButton/DownloadReceipt.jsx";




function Transactions(props) {
  const [data, setData] = useState([]);
  const [detail, setDetail] = useState({});
  const [open, setOpen] = useState(false);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(5);
  const [filterNumber, setFilterNumber] = useState(0);
  const [resFull, setResFull] = useState({});
  const onCloseModal = () => setOpen(false);
  const [filterMessage,setFilterMessage] = useState(false);

  useEffect(() => {
    getTransactions(props.userId, page, size, filterNumber).then((response) => {
      // let orden = response.data.content.sort(
      //   (a, b) => new Date(b.date) - new Date(a.date)
      // );
      // setData(response.data.content);
      
      setData(response.data.content);
      if(response.data.content.length === 0){
        toast.warn('No existen datos para el filtro seleccionado!', {
          position: "top-center",
          autoClose: 1200,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
          // eslint-disable-next-line no-undef
          transition: Bounce,
          })
          setFilterMessage(true);
      }else{
        setFilterMessage(false);
      }
      setResFull(response.data);
    });
  }, [page, filterNumber]);

  function handleChange(e) {
    setFilterNumber(e.target.value);
  }

  async function handleClick(idTransaction, userId) {
    setOpen(true);
    try {
      const response = await getTransactionDetail(userId, idTransaction);
      setDetail(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    
    <div className={style.transactionMainContainer}>
      <ToastContainer />
      
      <div className={style.mainActivity}>
        <h2 className={style.titless}>Tu última actividad</h2>
        <div className={style.mainFilterContainer}>
          <p style={{ textAlign: "center" }}>
            Pagina {resFull.number} /{ resFull.totalPages == 0 ? resFull.totalPages: resFull.totalPages - 1}
          </p>
          <div>
            <select
              id=""
              name=""
              className={style.selectItem}
              value={filterNumber}
              onChange={(e) => handleChange(e)}
            >
              <option className={style.optionItem} value="0">
                Seleccione el filtro...
              </option>
              <option className={style.optionItem} value="1">
                $0 - $1000
              </option>
              <option className={style.optionItem} value="2">
                $1.000 - $5.000
              </option>
              <option className={style.optionItem} value="3">
                $5.000 - $20.000
              </option>
              <option className={style.optionItem} value="4">
                $20.000 - $100.000
              </option>
              <option className={style.optionItem} value="5">
                Mayores a $100.000
              </option>
              <option className={style.optionItem} value="6">
                Mis ingresos
              </option>
              <option className={style.optionItem} value="7">
                Mis egresos
              </option>
            </select>
          </div>
        </div>
      </div>
      {!data || data.length === 0 ? (
        <div className={style.transactionsBackground}>
          <h3 className={style.noTransactions} >
            {filterMessage ? "Nada por aqui.. 😑" : "Parece que aún no has realizado ninguna transacción...🔎"}
          </h3>
          <p>
            
          </p>
        </div>
      ) : (
        <>
          <section className={style.transactionSection}>
            <div className={style.test}>
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
                    <DownloadReceipt data={transaction}></DownloadReceipt>
                  </div>
                  
                </div>
                
              ))}
            </div>
            <div>
              <button
                onClick={() => {
                  if (page <= 0) {
                    setPage(0);
                    return;
                  }
                  setPage(page - 1);
                }}
                className={style.btnPage}
              >
                Anterior pagina
              </button>
              <button
                onClick={() => {
                  if (page >= resFull.totalPages - 1) {
                    setPage(0);
                    return;
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
