import style from "./homepage.module.css";

export default function HomePage() {
  return (
    <>
      <div className={style.homeMainContainer}>
        <div className={style.textContainerHome}>
          <h1 className="titles">
            Safe Wallet, Lorem ipsum dolor, sit amet consectetur adipisicing
            elit. Totam, esse.
          </h1>
          <p className="normalText">
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Officia,
            sit quis porro quae reiciendis dolorem?
          </p>
        </div>
        <div>
          <img className={style.cardImage} src="/card.png" alt="" />
        </div>
      </div>
      <section>
        <div className={style.titleServices}>
          <h2 className="titles">¿Que ofrecemos?</h2>
        </div>

        <div className={style.servicesContainer}>
          <div className={`${style.serviceChild} ${style.a}`}>
            <div>
              <img src="/serviceIcon1.png" height={"64"} width={"64"} alt="" />
            </div>
            <div>
              <h3 className="titles">Garantia de seguridad</h3>
              <p className="normalText">Tus datos y tus fondos siempre estaran protegidos.</p>
            </div>
          </div>

          <div className={`${style.serviceChild} ${style.a}`}>
            <div>
              <img src="/serviceIcon2.png" height={"64"} width={"64"} alt="" />
            </div>
            <div>
              <h3 className="titles">Soporte</h3>
              <p className="normalText">Soporte ilimitado, no importa el dia ni la hora</p>
            </div>
          </div>
          <div className={`${style.serviceChild} ${style.a}`}>
            <div>
              <img src="/serviceIcon3.png" height={"64"} width={"64"} alt="" />
            </div>
            <div>
              <h3 className="titles">Transferencias instantaneas</h3>
              <p className="normalText">Transferi y solicita dinero al instante</p>
            </div>
          </div>
        </div>
      </section>
      <section>
        <div className={style.secondCardContainer}>
          <div className={style.cardActionContainer}>
            <h2 className="titles">¡Virtualiza tus tarjetas!</h2>
            <p>
              Con Safe Wallet virtualiza todas tus tarjetas, para que puedas
              tener todo en un mismo lugar tu comodidad es nuestra prioridad
            </p>
            <a href="" className="styleOff">
              <button className="primarybtn btnefect">
                ¡Comenza YA esta experiencia!
              </button>
            </a>
          </div>
          <div>
            <img  className={style.card2}src="/card2.png" alt="" />
          </div>
        </div>
      </section>
    </>
  );
}
