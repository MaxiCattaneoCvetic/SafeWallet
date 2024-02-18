import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import style from "./mainNavigationUser.module.css";


const DropdownMenu = (props) => {
  const [isOpen, setIsOpen] = useState(false);
  const [initials,setInitials] = useState("")


  function getInitials(name,lastname) {
    let firstLetter = name.charAt(0).toUpperCase();
    let secondLetter = lastname.charAt(0).toUpperCase();
    let total = firstLetter+secondLetter
    setInitials(total)
  }

  useEffect(()=>{
    const data = JSON.parse(sessionStorage.getItem("user"))
    getInitials(data.name,data.lastName)
  },[])

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
            {initials}
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
