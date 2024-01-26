import AccountBalance from "../../components/accountBalance/AccountBalance.jsx"
import CardAccount from "../../components/cardAccount/CardAccount.jsx"
import style from "./account.module.css"
import NavBar from "../../components/navBar/NavBar.jsx"

export default function Account() {
  return (
    <>
    <NavBar></NavBar>
      <div className={style.backgroundAccount}>
				<div className={style.containerHello}>
        <h1 className={style.helloUser}>Hola Maximiliano!</h1>
        <h4>Que gusto tenerte de vuelta 😃</h4>
				</div>
      <section className={style.balanceContainer}>
				<AccountBalance/>
			</section>
      <section>
      <CardAccount/>
      </section>
			</div>
    </>
  );
}
