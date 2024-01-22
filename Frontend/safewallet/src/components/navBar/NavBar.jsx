"use client";
import { useState } from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { IoMenuSharp } from "react-icons/io5";
import { IoMdClose } from "react-icons/io";
import Modal from "../modal/Modal.jsx";
import Login from "../login/Login.jsx";
import style from "./navbar.module.css";


export default function NavBar() {
  const [isOpen, setIsOpen] = useState(false);
  const [isOpenMenu, setIsOpenMenu] = useState(false);


  const toggleMenu = () => {
    setIsOpenMenu(!isOpenMenu);
  };

  const navMenuMobile = (
    <>
      {/* MENU HAMBURGUESA */}
      <header className={style.menuBurger}>
        <div className={style.logoContainerMobile}>
          <GrGoogleWallet />
          <p>SafeWallet</p>
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
              <a href="">Inicio</a>
              <a href="">Nosotros</a>
              <a href="">Precio</a>
              <button
                className="primarybtnMOBILE"
                onClick={(e) => {
                  setIsOpenMenu(false); 
                  setIsOpen(true);
                }}
              >
                Iniciar
              </button>
              <button
                className="primarybtnMOBILE"
                onClick={() => {
                  //
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
                <GrGoogleWallet />
                <p>SafeWallet</p>
              </div>

              <div className={style.navLinks}>
                <a href="">Inicio</a>
                <a href="">Nosotros</a>
                <a href="">Precio</a>
              </div>

              <div className={style.userPanelNav}>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    setIsOpen(true);
                  }}
                  className="primarybtn btnefect"
                >
                  Ingresar
                </button>
                <button
                  className="primarybtn btnefect"
                  onClick={() => {
                    //
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
