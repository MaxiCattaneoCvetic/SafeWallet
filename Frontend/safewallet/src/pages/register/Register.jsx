"use client";
import Modal from "../../components/modal/Modal.jsx";
import style from "./register.module.css";
import { useEffect, useRef, useState } from "react";
import swal from "sweetalert";
import { fetchuserFull} from "../../api/registerFetch.js";
import { REGISTER_USER_FULL } from "../../URLS/URL.js";
import { useNavigate } from "react-router-dom";
import { SpinnerCircular } from "spinners-react";

export default function Register() {
  const navigate = useNavigate();
  const [contador, setContador] = useState(0);
  const [error2, setError2] = useState({
    errorEmail: false,
    errorPassword: false,
  });
  const [error, setError] = useState("");
  const [errorUserExist, setErrorUserExist] = useState(false);
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
  const [loading, setLoading] = useState(null);

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
      [e.target.name]: e.target.value.toLowerCase(),
    }));
  };

  async function handleRegister(e) {
    e.preventDefault();
    // Contruimos objeto para userDataFull
    const userv = {
      username: data.email,
      password: data.password,
      roles: ["user"],
      name: data.name,
      lastName: data.lastname,
      email: data.email,
      phone: data.phone,
      dni: data.dni,
    };

    try {
      const response = await fetchuserFull(userv, REGISTER_USER_FULL);
      setLoading(true);
      if (response.status === 201) {
        successRegister();
        setLoading(false)
      } else {
        setErrorUserExist(true);
        setError(response.response.data);
      }
    } catch (error) {
      swal("¡Ops, algo anda mal!", "Tu cuenta no pudo ser registrada por un problema en el servidor. \n Por favor contacta con un administrador.", "error").then(()=>navigate("/"))
    }
  }

  function successRegister() {
    swal({
      title: "¡Registro exitoso!",
      text: "Vuelve al inicio e inicia sesión con tus datos",
      icon: "success",
      button: "Volver al inicio",
    }).then(() => {
      setLoading(false);
      navigate("/");
    });
  }
  const validateAndNext = () => {
    const currentStepData = data[Object.keys(data)[contador]];

    if (contador === 3 && !data.email.includes("@")) {
      setError2((prevError) => ({
        ...prevError,
        errorEmail: true,
        errorPassword: false,
      }));
      return;
    } else {
      setError2((prevError) => ({
        ...prevError,
        errorEmail: false,
        errorPassword: false,
      }));
    }

    if (contador === 5) {
      if (data.password != password2) {
        setError2((prevError) => ({
          ...prevError,
          errorEmail: false,
          errorPassword: true,
        }));
        return;
      } else if (data.password === password2) {
        setError2((prevError) => ({
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
            {error2.errorEmail ? (
              <p className="textError" id="errorRegister">
                El email no es valido
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
            {error2.errorPassword ? (
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
                  setErrorUserExist(false);
                }}
              >
                Volver
              </button>
              {errorUserExist ? <p className="textError">{error}</p> : ""}
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
      {loading == true ? (
        <div className="spinner">
          {" "}
          <SpinnerCircular />
        </div>
      ) : (
        <div className={style.mainPage}>
          <Modal
            title="Registro"
            children={modalChildrenRegister}
            onClick={() => {
              navigate("/");
            }}
          ></Modal>
        </div>
      )}
    </>
  );
}
