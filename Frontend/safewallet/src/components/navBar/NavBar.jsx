"use client";
import { useEffect, useState,useRef } from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { IoMenuSharp } from "react-icons/io5";
import { IoMdClose } from "react-icons/io";
import style from "./navbar.module.css";
import { useNavigate } from "react-router-dom";
// import { APPLICATION_LOGIN } from "../../URLS/URL.js";
import NoAuth from "./Noauth.jsx";
import DropdownMenu from "../navBar/navBarUser/DropdownMenu.jsx";
// import useAuth from "../../security/UseAuth.jsx";



export default function NavBar(props) {
  const [isOpenMenu, setIsOpenMenu] = useState(false);
  const navigate = useNavigate();
  const [isLogin,setIsLogin] = useState(false)


  useEffect(()=>{
    if(sessionStorage.getItem("isLogin") == "true" && sessionStorage.getItem("token") != null && sessionStorage.getItem("user") != null){
      setIsLogin(true)
    }else{
      setIsLogin(false)
      
    }
  },[])




  const toggleMenu = () => {
    setIsOpenMenu(!isOpenMenu);
  };

  // Menu mobile
  const navMenuMobile = (
    <>
      {/* MENU HAMBURGUESA */}
      <header className={style.menuBurger}>
        <div className={style.logoContainerMobile}>
          <a href="/" className="styleOff">
            <GrGoogleWallet /> SafeWallet
          </a>
          {!isOpenMenu ? (
            <div className={style.menuIcon} onClick={toggleMenu}>
              <IoMenuSharp />
            </div>
          ) : (
            <IoMdClose
              onClick={() => {
                setIsOpenMenu(false);
              }}
            />
          )}
        </div>
        {isOpenMenu && (
          <>
            <div className={style.dropdownMenu}>
              <a href="/">Inicio</a>
              <a href="">Nosotros</a>
              <a href="">Precio</a>
              {isLogin ? <DropdownMenu   /> : <NoAuth />}
            </div>
          </>
        )}
      </header>
    </>
  );
  // end menu mobile

  return (
    <>
      <div>
        {/* Si la resolucion es par mobile se oculta desktop y se muestra mobile */}
        {navMenuMobile}
        <header className={style.headDesktop}>
          <nav className={props.value ? style.navbar2 : style.navbar}>
            <div className={style.logoContainer}>
              <a href="/" className="styleOff">
                <GrGoogleWallet /> SafeWallet
              </a>
            </div>
            <div className={style.navLinks}>
              <a href="/">Inicio</a>
              <a href="/">Nosotros</a>
              <a href="/">Precios</a>
            </div>
            <div className={style.userPanelNav}>
              {isLogin ? (
                <DropdownMenu  />
              ) : (
                <>
                  {" "}
                  <button
                    onClick={(e) => {
                      e.preventDefault();
                      navigate("/account")
                    }}
                    className="primarybtn btnefect"
                  >
                    Ingresar
                  </button>
                  <button
                    className="primarybtn btnefect"
                    onClick={() => {
                      navigate("/register");
                    }}
                  >
                    Registrarse
                  </button>
                </>
              )}
            </div>
          </nav>
        </header>
      </div>
    </>
  );
}
