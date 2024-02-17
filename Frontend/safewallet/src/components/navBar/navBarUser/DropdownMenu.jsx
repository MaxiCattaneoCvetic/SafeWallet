import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import style from "./mainNavigationUser.module.css";


const DropdownMenu = (props) => {
  const [isOpen, setIsOpen] = useState(false);


  return (
    <>
      <div className={style.containerSession}>
        <div
          className={style.avatarBackground}
          onClick={() => {
            setIsOpen(!isOpen);
          }}
        >
          <p className={style.avatarBackground}>
            MC
          </p>
        </div>
        {isOpen && (
          <div className={style.linkDropMenu}>
            <Link to={""} className="styleOff"><p className="greenHoover">Mi perfil</p></Link>
            <Link to={""} className="styleOff"><p className="greenHoover">Mis transferencias</p></Link>
            <Link to={""} className="styleOff"><p className="greenHoover">Cerrar sesión</p></Link>
          </div>
        )}
      </div>

    </>
  );
};

export default DropdownMenu;
