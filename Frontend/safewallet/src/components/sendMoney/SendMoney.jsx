import { useState } from "react";
import sendMoneyApi from "../../api/sendMoneyApi.js";
import style from "./sendMoney.module.css"
export default function SendMoney(props) {
	const [cbuTo, setCbu] = useState();
	const [cbuFrom, setCbuFrom] = useState(props.cbuFrom);
	const [alias, setAlias] = useState();
	const [amount, setAmount] = useState();


	function handleSubmit(e) {
		e.preventDefault();
		sendMoneyApi(amount,cbuFrom,cbuTo)
		
	}

	return(
		<>
		<form action="" className={style.formInput}>
			<label htmlFor="">Ingrese el Cbu o el alias</label>
			<input type="text" onChange={(e)=>{setCbu(e.target.value)}}  placeholder="Cbu o alias"/>
			<label htmlFor="">Ingrese el monto</label>
			<input type="number" onChange={(e)=>{setAmount(e.target.value)}} placeholder="Ingrese el monto $"/>
			<button  type="submit" onClick={handleSubmit}>Enviar dinero</button>
		</form>
		
		</>
	)

}