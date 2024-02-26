import style from "./cardAccount.module.css";

export default  function CardAccount () {
	return(
		<>
		<h2 className="titles">Mis tarjetas:</h2>
		<div className={style.mainTargetContainer}>
		<div className={style.targetConteiner}>
			<div>
				<img src="/targets.png" alt="mis tarjetas" />
			</div>

		<div className={style.containercard}>
		<div className={style.buttonsContainer}>
		<button className="scondbtn">Ver tarjetas activas</button>
		<button className="scondbtn">Quitar tarjeta</button>
		</div>
		<button className="scondbtn">Agregar nueva tarjeta</button>
		</div>

		</div>

		</div>

		</>
	)
}