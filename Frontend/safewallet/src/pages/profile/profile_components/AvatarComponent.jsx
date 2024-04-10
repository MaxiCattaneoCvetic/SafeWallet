import style from "../profile.module.css";
import { useEffect, useState } from "react";
export default function AvatarComponent() {
  const [contador, setContador] = useState(1);
  const [saveContador, setSaveContador] = useState(
    sessionStorage.getItem("avatar")
  );

  return (
    <>
      <div className={style.avatarConteiner}>
        <img src={`/avatar${contador}.png`} alt="avatar-profile" />
        <button
          className={style.avatarImg}
          onClick={(e) => {
            e.preventDefault();
            if (contador >= 10) {
              setContador(1);
            } else {
              setContador(contador + 1);
            }
          }}
        >
          Cambiar de avatar
        </button>
      </div>
    </>
  );
}
