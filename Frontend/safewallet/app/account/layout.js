import style from "./account.module.css"


export default function AccountLayout({
  children, // will be a page or nested layout
}) {
  return (
    <section className={style.backgroundAccount}>
      {children}
    </section>
  )
}