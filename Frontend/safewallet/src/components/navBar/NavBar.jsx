"use client";
import { useEffect, useState } from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { IoMenuSharp } from "react-icons/io5";
import { IoMdClose } from "react-icons/io";
import style from "./navbar.module.css";
import { useNavigate } from "react-router-dom";
// import { APPLICATION_LOGIN } from "../../URLS/URL.js";
import NoAuth from "./Noauth.jsx";
import DropdownMenu from "../navBar/navBarUser/DropdownMenu.jsx";
import { useAuth } from "../../context/AuthProvider.jsx";
// import getToken from "../../keycloak/config/keycloak-config.js";
import keylockInit  from "../../security/keyloack.js"
import Keycloak from "keycloak-js";
const key = keylockInit();



export default function NavBar() {
  const [isOpenMenu, setIsOpenMenu] = useState(false);
  const navigate = useNavigate();
  const [isAuth, setIsAuth] = useState(false);
  const { user } = useAuth();

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
              {user ? <DropdownMenu /> : <NoAuth />}
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
      <button onClick={()=>keycloak.logout()}>AAAAAAAAAAA</button>
        {/* Si la resolucion es par mobile se oculta desktop y se muestra mobile */}
        {navMenuMobile}
        <header className={style.headDesktop}>
          <nav className={style.navbar}>
            <div className={style.logoContainer}>
              <a href="/" className="styleOff">
                <GrGoogleWallet /> SafeWallet
              </a>
            </div>
            <div className={style.navLinks}>
              <a href="/">Inicio</a>
              <a href="/">Nosotros</a>
              <a href="/">Precio</a>
              {/* <button className="primarybtn"
              
                onClick={() => {
                  login();
                }}
              >Login</button>
              <button className="primarybtn"
                onClick={() => {
                  callApix();
                }}
              >Call api</button>
              <button className="primarybtn"
                onClick={() => {
                  getUser();
                }}
              >Get user</button> */}
            </div>

            <div className={style.userPanelNav}>
              {/* Si Auth es true quiere decir que  hay un user en sesion, por lo tanto mostramos el dropdown
              de lo contrario mostramos el login o register */}

              {isAuth ? (
                <DropdownMenu />
              ) : (
                <>
                  {" "}
                  <button
                    onClick={(e) => {
                      e.preventDefault();
                      keylockInit()
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
