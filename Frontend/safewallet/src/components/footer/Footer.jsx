// Footer.js
import React from "react";
import { GrGoogleWallet } from "react-icons/gr";
import { FaGithub } from "react-icons/fa";
import { CiLinkedin } from "react-icons/ci";
import { GiThreeFriends } from "react-icons/gi";
import style from "./footer.module.css";

export default function Footer() {
    return (
        <footer className={style.footer}>
            <div className={style.footerContainer}>
                <div>
                    <GrGoogleWallet />
                    <p className="normalTextFooter">SafeWallet</p>
                    <p className="normalTextFooter">Tu billetera de confianza</p>
                </div>
                <div>
                    <p className="normalTextFooter">¡Nuestras redes!</p>
                    <FaGithub  className={style.redIcon}/>
                    <CiLinkedin className={style.redIcon} />
                    <GiThreeFriends  className={style.redIcon}/>
                </div>
            </div>
        </footer>
    );
}
