// import React, { useState } from "react";
// import style from "./login.module.css";
// import { URL_LOGIN, CLIENT_ID_KEYCLOAK } from "../../URLS/URL.js";



// export default function Login() {
//   const [email, setEmail] = useState("");
//   const [emailRecover, setEmailRecover] = useState("");
//   const [password, setPassword] = useState("");
//   const [error, setError] = useState(null);
//   const [loginForm, setLoginForm] = useState(true);

//   const handleLoginSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await fetch(URL_LOGIN, {
//         method: "POST",
//         headers: {
//           "Content-Type": "application/x-www-form-urlencoded",
//         },
//         body: new URLSearchParams({
//           client_id: CLIENT_ID_KEYCLOAK,
//           grant_type: "password",
//           username: email,
//           password: password,
//         }),
//       });

//       const responseData = await response.json();
//       if (response.ok) {
//         console.log(responseData);
//         let index = email.indexOf("@");
//         let name = email.substring(0, index);
//         confirm("Bienvenido " + name);
//         setError("");
//         // ACA TENGO PARA DECODIFICAR EL TOKEN
//         // hay que ver como guardar datos en variable global para usar en la app
//         // const token = responseData.access_token;
//         // const decodedToken = jwt.decode(token);
//         // console.log(decodedToken);
//       }

//       if (!response.ok) {
//         setError(responseData.error_description);
//         console.log(responseData);
//       }

//       // Aquí puedes manejar la respuesta del servidor como desees.
//     } catch (error) {
//       // Puedes manejar el error de alguna manera (por ejemplo, mostrar un mensaje de error al usuario).
//     }
//   };


//   const handleRecoverSubmit = async (e) =>{
//     e.preventDefault();
//     //implemetar logica para recuperar email
//     //falta back
//     console.log("recuperando info de: " + emailRecover)
//   } 

//   return (
//     <div className={style.containerLogin}>
//       {loginForm ? (
//         <form onSubmit={handleLoginSubmit} className={style.form}>
//           <div className={style.inputContainer}>
//             <label htmlFor="email" className={style.label}>
//               Correo electrónico
//             </label>
//             <input
//               type="text"
//               id="email"
//               className={style.input}
//               required
//               placeholder="Ingrese su email"
//               value={email}
//               onChange={(e) => setEmail(e.target.value)}
//             />
//           </div>

//           <div className={style.inputContainer}>
//             <label htmlFor="password" className={style.label}>
//               Contraseña
//             </label>
//             <input
//               type="password"
//               id="password"
//               className={style.input}
//               required
//               placeholder="Ingrese su contraseña"
//               value={password}
//               onChange={(e) => setPassword(e.target.value)}
//             />
//           </div>

//           <button type="submit" className="primarybtn btnefect loginBTN">
//             Ingresar
//           </button>
//           <a href="" className="normalText" onClick={(e)=>{
//             e.preventDefault()
//             setLoginForm(false)
//           }}>
//             Olvide mi contraseña
//           </a>

//           {error && <p className="textError">{error}</p>}
//         </form>
//       ) : (
//         <form onSubmit={handleRecoverSubmit} className={style.form}>
//           <div className={style.inputContainer}>
//             <label htmlFor="emailRecover" className={style.label}>
//               Correo electrónico
//             </label>
//             <input
//               type="text"
//               id="emailRecover"
//               className={style.inputRecover}
//               required
//               placeholder="Ingrese el correo para recuperar"
//               value={emailRecover}
//               onChange={(e) => setEmailRecover(e.target.value)}
//             />
//           </div>
//           <button type="submit" className="primarybtn btnefect loginBTN">
//             Solicitar
//           </button>
//           <button  className="primarybtn btnefect loginBTN" 
//           onClick={(e)=>{
//             e.preventDefault();
//             setLoginForm(true);
//           }}
//           >
//             Volver
//           </button>

//           {error && <p className="textError">{error}</p>}
//         </form>
//       )}

//       <img src="/card2.png" className={style.imgLogin} alt="" />
//     </div>
//   );
// }
