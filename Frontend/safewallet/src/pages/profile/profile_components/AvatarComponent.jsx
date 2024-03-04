import style from "../profile.module.css";
import { useEffect, useState } from "react";
export default function AvatarComponent() {
	const [contador, setContador] = useState(1);
  return (
    <>
      <div className={style.avatarConteiner}>
        <img src={`/avatar${contador}.png`} alt="avatar-profile" />
        <button
          className={style.avatarImg}
          onClick={(e) => {
            console.log(contador);
            e.preventDefault();
            if (contador >= 6) {
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
