"use client";
import { useState } from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { IoMenuSharp } from "react-icons/io5";
import { IoMdClose } from "react-icons/io";
import Modal from "../modal/Modal.jsx";
import Login from "../login/Login.jsx";
import style from "./navbar.module.css";
import { useNavigate } from "react-router-dom";
import { URL_LOGIN } from "../../URLS/URL.js";

export default function NavBar() {
  const [isOpen, setIsOpen] = useState(false);
  const [isOpenMenu, setIsOpenMenu] = useState(false);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setIsOpenMenu(!isOpenMenu);
  };

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
              <button
                className="primarybtnMOBILE"
                onClick={(e) => {
                  e.preventDefault();
                  navigate(URL_LOGIN);
                }}
              >
                Ingresar
              </button>
              <button
                className="primarybtnMOBILE"
                onClick={() => {
                  navigate("/register");
                }}
              >
                Registrarse
              </button>
            </div>
          </>
        )}
      </header>
    </>
  );

  return (
    <>
      {isOpen ? (
        <Modal
          onClick={() => {
            setIsOpen(false);
          }}
          title={<Login />}
        ></Modal>
      ) : (
        ""
      )}

      <div>
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
            </div>

            <div className={style.userPanelNav}>
              <button
                onClick={(e) => {
                  e.preventDefault();
                  window.open(URL_LOGIN, "_blank");
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
            </div>
          </nav>
        </header>
      </div>
    </>
  );
}
