import { useState } from "react";
import style from "./profile.module.css";
import NavBar from ".././../components/navBar/NavBar.jsx"



function Profile() {
	const [contador,setContador] = useState(1)
  return (
    <>
      <section>
				<NavBar></NavBar>
        <div className={style.avatarConteiner}>
				<h2 className={style.titleAvatar}>Mi perfil</h2>
          <img src={`../../../public/avatar${contador}.png`} alt="avatar-profile" />
					<button className={style.avatarImg} onClick={(e)=>{
						console.log(contador);
						e.preventDefault()
						if(contador >= 6){
							setContador(1)
						}else{
							setContador(contador + 1)
						}
					}}> Cambiar de avatar </button>
					<h3>Aqui puedes ver y editar tus datos</h3>
        </div>
        <div className={style.containerInfo}>
          <div className={style.containerInfoV}>
            <label htmlFor="name">
              Nombre
              <input
                className={style.inputInformation}
                value={"Maximiliano"}
								id="name"
              ></input>
            </label>
            <label htmlFor="">Apellido
              <input
                className={style.inputInformation}
                value={"Cattaneo"}
              ></input>
            </label>
            <label htmlFor="">Telefono
              <input
                className={style.inputInformation}
                value={"1521551561"}
              ></input>
            </label>
          </div>

          <div className={style.containerInfoV}>
            <label htmlFor="">DNI
            <input className={style.inputInformation} value={"38489151"}></input>
						</label>
            <label htmlFor="">CBU
            <input className={style.inputInformation} value={"2151516515165556"}></input>
						</label>
            <label htmlFor="">ALIAS
            <input className={style.inputInformation} value={"BUERT.PUERTA.HOMBRO"}></input>
						</label>
          </div>
        </div>
      </section>
    </>
  );
}

export default Profile;
