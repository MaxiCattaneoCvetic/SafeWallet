import { useState } from "react";
import style from "./profile.module.css";
import NavBar from ".././../components/navBar/NavBar.jsx"
import upperCase from "../../functions/upperCase.js"




function Profile(props) {
	const [contador,setContador] = useState(1)
  
  return (
    <>
      <section>
				<NavBar value={true}></NavBar>
        <div className={style.avatarConteiner}>
				<h2 className={style.titleAvatar}>Mi perfil</h2>
          <img src={`/avatar${contador}.png`}  alt="avatar-profile" />
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
                value={upperCase(props.user.name)}
								id="name"
              ></input>
            </label>
            <label htmlFor="">Apellido
              <input
                className={style.inputInformation}
                value={upperCase(props.user.lastName)}
              ></input>
            </label>
            <label htmlFor="">Telefono
              <input
                className={style.inputInformation}
                value={props.user.phone}
              ></input>
            </label>
          </div>
          <div className={style.containerInfoV}>
            <label htmlFor="">DNI
            <input className={style.inputInformation} value={props.user.dni}></input>
						</label>
            <label htmlFor="">CBU
            <input className={style.inputInformation} value={props.user.cbu}></input>
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
