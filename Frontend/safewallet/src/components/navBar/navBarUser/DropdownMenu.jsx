import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import style from "./mainNavigationUser.module.css";
import  logOut  from "../../../functions/logOut";
const DropdownMenu = (props) => {
  const [isOpen, setIsOpen] = useState(false);
  const [initials,setInitials] = useState("")



  function getInitials(name,lastname) {
    let firstLetter = name.charAt(0).toUpperCase();
    let secondLetter = lastname.charAt(0).toUpperCase();
    let total = firstLetter+secondLetter
    setInitials(total)
  }


  async function handleLogOut() {
    const response = logOut()
    if(response.status === 200) {
      sessionStorage.clear();
      localStorage.clear();
      window.location.replace("https://safewallet-sooty.vercel.app/");
    }
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
            <Link to={"/profile"} className="styleOff"><p className="greenHoover">Mi perfil</p></Link>
            <Link to={"/account"} className="styleOff"><p className="greenHoover">Consultar balance</p></Link>
            <Link to={"/myCards"} className="styleOff"><p className="greenHoover">Mis tarjetas</p></Link>
            <p className="greenHoover" onClick={handleLogOut}>Cerrar Sesión</p>
            
          </div>
        )}
      </div>

    </>
  );
};

export default DropdownMenu;
