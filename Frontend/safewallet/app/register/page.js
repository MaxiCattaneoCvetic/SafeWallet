"use client";
import Modal from "@/components/Modal/Modal";
import style from "./register.module.css";
import { useEffect, useRef, useState } from "react";
import { useRouter } from "next/navigation";
import swal from "sweetalert";
import { fetchuserFull, fetchKeyc } from "../api/RegisterFetch";
import { REGISTER_USER_KEYCLOAK, REGISTER_USER_FULL } from "@/URLS/URL";

export default function Register() {
  const router = useRouter();
  const [contador, setContador] = useState(0);
  const [error, setError] = useState({
    errorEmail: false,
    errorPassword: false,
  });
  const [data, setData] = useState({
    name: "",
    lastname: "",
    dni: "",
    email: "",
    phone: "",
    password: "",
  });
  const [password2, setPassword2] = useState("");
  const inputRef = useRef(null);

  useEffect(() => {
    try {
      inputRef.current.focus();
    } catch (e) {
      return;
    }
  }, [contador]);

  if (contador < 0) {
    setContador(0);
  } else if (contador > 6) {
    setContador(6);
  }

  const handleChange = (e) => {
    setData((prevData) => ({
      ...prevData,
      [e.target.name]: e.target.value,
    }));
  };

  async function handleRegister(e) {
    e.preventDefault();
    // Contruimos objeto para keyc
    const user = {
      username: data.email, // Puedes utilizar el email como nombre de usuario
      firstName: data.name,
      lastName: data.lastname,
      password: data.password,
      email: data.email,
      role: ["user"],
    };

    // Contruimos objeto para userDataFull
    const userv = {
      name: data.name, // Puedes utilizar el email como nombre de usuario
      lastName: data.lastname,
      email: data.email,
      phone: data.phone,
      dni: data.dni,
    };

    //fetchuserFull(userv,REGISTER_USER_FULL)
    try {
      const response = await fetchKeyc(user, REGISTER_USER_KEYCLOAK);
      console.log(response);
      // let responseKeycloak = response.data;
      // const response2 = await fetchuserFull(userv, REGISTER_USER_FULL);
      // let responseFULL = response.data;

      if (
        responseKeycloak === "User exist already!" 
        //&& responseFULL === "El DNI o el correo ya fue registrado"
      ) {
        alert("El usuario ya existe");
      } else {
        successRegister();
      }

      // Continuar con el manejo de la respuesta aquí
    } catch (error) {
      // Manejar errores aquí
    }
  }

  function successRegister() {
    swal({
      title: "¡Registro exitoso!",
      text: "Vuelve al inicio e inicia sesión con tus datos",
      icon: "success",
      button: "Volver al inicio",
    }).then(() => {
      router.push("/");
    });
  }
  const validateAndNext = () => {
    const currentStepData = data[Object.keys(data)[contador]];

    if (contador === 3 && !data.email.includes("@")) {
      setError((prevError) => ({
        ...prevError,
        errorEmail: true,
        errorPassword: false,
      }));
      return;
    } else {
      setError((prevError) => ({
        ...prevError,
        errorEmail: false,
        errorPassword: false,
      }));
    }

    if (contador === 5) {
      if (data.password != password2) {
        setError((prevError) => ({
          ...prevError,
          errorEmail: false,
          errorPassword: true,
        }));
        return;
      } else if (data.password === password2) {
        setError((prevError) => ({
          ...prevError,
          errorEmail: false,
          errorPassword: false,
        }));
      }
    }
    if (!currentStepData) {
      swal("", "Completa todos los campos antes de continuar", "error");
      return;
    }
    setContador(contador + 1);
  };

  const modalChildrenRegister = (
    <div className={style.containerForm}>
      <form action="" className={style.form} onSubmit={handleRegister}>
        {contador === 0 && (
          <div>
            <label className={style.label}>Nombre</label>
            <input
              className={style.input}
              type="text"
              id="name"
              name="name"
              placeholder="Ingrese su nombre"
              title="Escriba solo letras por favor"
              pattern="[a-zA-Z]+"
              onChange={handleChange}
              value={data.name}
              ref={inputRef}
            />
          </div>
        )}
        {contador === 1 && (
          <div>
            <label className={style.label} htmlFor="lastname">
              Apellido
            </label>
            <input
              className={style.input}
              type="text"
              id="lastname"
              name="lastname"
              placeholder="Ingrese su apellido"
              pattern="[a-zA-Z]+"
              onChange={handleChange}
              value={data.lastname}
              ref={inputRef}
            />
          </div>
        )}
        {contador === 2 && (
          <div>
            <label className={style.label} htmlFor="dni">
              DNI
            </label>
            <input
              className={style.input}
              type="number"
              id="dni"
              name="dni"
              placeholder="Ingrese su DNI"
              onChange={handleChange}
              value={data.dni}
              ref={inputRef}
            />
          </div>
        )}
        {contador === 3 && (
          <div>
            <label className={style.label} htmlFor="email">
              Email
            </label>
            <input
              className={style.input}
              type="email"
              id="email"
              name="email"
              placeholder="Ingrese su email"
              onChange={handleChange}
              value={data.email}
              ref={inputRef}
            />
            {error.errorEmail ? (
              <p className="textError" id="errorRegister">
                Por favor ingrese un email valido
              </p>
            ) : (
              ""
            )}
          </div>
        )}
        {contador === 4 && (
          <div>
            <label className={style.label} htmlFor="phone">
              Ingrese su número de telefono
            </label>
            <input
              className={style.input}
              type="number"
              id="phone"
              name="phone"
              placeholder="Ingrese su telefono"
              onChange={handleChange}
              value={data.phone}
              ref={inputRef}
            />
          </div>
        )}
        {contador === 5 && (
          <>
            <div>
              <label className={style.label} htmlFor="password">
                Contraseña
              </label>
              <input
                className={style.input}
                type="password"
                id="password"
                name="password"
                placeholder="Ingrese su contraseña"
                onChange={handleChange}
                value={data.password}
                ref={inputRef}
              />
            </div>
            <div>
              <label className={style.label} htmlFor="password">
                Repita la contraseña
              </label>
              <input
                className={style.input}
                type="password"
                id="password2"
                name="password2"
                placeholder="Ingrese su contraseña"
                onChange={(e) => {
                  setPassword2(e.target.value);
                }}
                value={password2}
              />
            </div>
            {error.errorPassword ? (
              <p className="textError" id="errorRegister">
                Las contraseñas no coinciden
              </p>
            ) : (
              ""
            )}
          </>
        )}

        {contador === 6 && (
          <>
            <h2>¡Gracias por confiar en nosotros!</h2>{" "}
            <div className={style.finalRegister}>
              <button className="primarybtn btnefect" type="submit">
                Registrarse
              </button>
              <button
                className="primarybtn btnefect"
                onClick={(e) => {
                  e.preventDefault();
                  setContador(contador - 1);
                }}
              >
                Volver
              </button>
            </div>
          </>
        )}
        {contador !== 6 && (
          <>
            <button
              className="primarybtn btnefect"
              onClick={(e) => {
                e.preventDefault();
                validateAndNext();
              }}
            >
              Next
            </button>
            <button
              className="primarybtn btnefect"
              onClick={(e) => {
                e.preventDefault();
                setContador(contador - 1);
              }}
            >
              Anterior
            </button>
          </>
        )}
      </form>
      <img className={style.registerImg} src="/register.png" alt="" />
    </div>
  );

  return (
    <>
      <Modal
        title="Registro"
        children={modalChildrenRegister}
        onClick={() => {
          router.push("/");
        }}
      ></Modal>
    </>
  );
}
