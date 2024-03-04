import { useEffect, useState } from "react";
import style from "./profile.module.css";
import NavBar from ".././../components/navBar/NavBar.jsx";
import { SpinnerCircular } from "spinners-react";




import AvatarComponent from "./profile_components/AvatarComponent.jsx";
import InputsInformation from "./profile_components/InputsInformation.jsx";

function Profile(props) {

  return (
    <>
      {props.user ? (
        <section>
          <NavBar value={true}></NavBar>
          <h2 className={style.titleAvatar}>Mi perfil</h2>
          <h3 className={style.titleAvatarh3}>
            Aqui puedes ver y editar tus datos
          </h3>
          <div className={style.mainContainerProfile}>
            <AvatarComponent />
            <InputsInformation user={props.user}/>
          </div>
        </section>
      ) : (
        <div className="spinner">
          <SpinnerCircular />
        </div>
      )}
    </>
  );
}

export default Profile;
