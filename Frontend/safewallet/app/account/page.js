"use client";
import AccountBalance from "@/components/accountBalance/AccountBalance";
import style from "./account.module.css";
import CardAccount from "@/components/cardAccount/CardAccount";

export default function Account() {
  return (
    <>
      <div className={style.backgroundAccount}>
				<div>
        <h1>Hola Maximiliano!</h1>
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
