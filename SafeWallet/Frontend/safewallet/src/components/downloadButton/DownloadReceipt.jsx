import { MdFileDownload } from "react-icons/md";
import getDocument from "../../api/getDocument";

function DownloadReceipt(props) {
  async function handleClick() {

		if(props.data.amount < 0 ){
			props.data.amount = props.data.amount * -1
		}

    const data = {
      nameFrom: props.data.to,
      accountFrom: props.data.to,
      nameTo: props.data.from,
      accountTo: props.data.from,
      amount: props.data.amount,
      date: props.data.date,
      detail: props.data.transferDetail,
    };

    try {
      const response = await getDocument(data); // Espera la respuesta del servidor
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', 'documento de transacción Nro.pdf');
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div style={{ cursor: "pointer", textAlign: "center", zIndex: "10" }}>
      <p onClick={handleClick}>
        Descargar <MdFileDownload />
      </p>
    </div>
  );
}

export default DownloadReceipt;
