"use client";
import { useEffect, useState } from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { IoMenuSharp } from "react-icons/io5";
import { IoMdClose } from "react-icons/io";
import Modal from "../Modal/Modal";
import Login from "../login/Login";
import style from "./navbar.module.css";
import { useParams, usePathname, useRouter } from "next/navigation";

export default function NavBar() {
  const [isOpen, setIsOpen] = useState(false);
  const [isOpenMenu, setIsOpenMenu] = useState(false);
  const pramams = usePathname(); // ver en que ruta estoy
  const router = useRouter();

  useEffect(() => {
    if (pramams == "/register") {
    }
  }, [pramams]);

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
                  router.push("/register");
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
      {pramams != "/register" ? (
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
                    router.push("/register");
                  }}
                >
                  Registrarse
                </button>
              </div>
            </nav>
          </header>
        </div>
      ) : (
        ""
      )}
    </>
  );
}
