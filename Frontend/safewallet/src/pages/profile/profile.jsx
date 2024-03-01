import { useEffect, useState } from "react";
import style from "./profile.module.css";
import NavBar from ".././../components/navBar/NavBar.jsx";
import upperCase from "../../functions/upperCase.js";
import { SpinnerCircular } from "spinners-react";
import patchUserEdit from "../../api/patchUserEdit.js";
import Swal from "sweetalert2";
import getUserData from "../../api/getUserData.js";

function Profile(props) {
  const [contador, setContador] = useState(1);



  function editAtribute(e, field) {
    e.preventDefault();

    Swal.fire({
      title: "Panel de edición",
      input: "text",
      inputAttributes: {
        autocapitalize: "off",
      },
      showCancelButton: true,
      confirmButtonText: "Confirmar nuevo",
      showLoaderOnConfirm: true,
      preConfirm: async (newValue) => {
        try {
          const dataToUpdate = {
            [field]: newValue,
          };
          const response = await patchUserEdit(props.user.id, dataToUpdate);
          if (response.status !== 200) {
            throw new Error("Error al actualizar la información");
          }
        } catch (error) {
          Swal.showValidationMessage(`Request failed: ${error}`);
        }
      },
      allowOutsideClick: () => !Swal.isLoading(),
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire(
          "¡Actualizado!",
          "La información ha sido actualizada con éxito.",
          "success"
        ).then(() => {
          getUserData(props.user.email).then((user) => {
            props.setUser(user);
          })
        });
      }
      
    });
  }

  return (
    <>
      {props.user ? (
        <section>
          <NavBar value={true}></NavBar>
          <h2 className={style.titleAvatar}>Mi perfil</h2>
          <h3 className={style.titleAvatarh3}>
            Aqui puedes ver y editar tus datos
          </h3>
          <div className={style.mainContainerProfile}>
            <div className={style.avatarConteiner}>
              <img src={`/avatar${contador}.png`} alt="avatar-profile" />
              <button
                className={style.avatarImg}
                onClick={(e) => {
                  console.log(contador);
                  e.preventDefault();
                  if (contador >= 6) {
                    setContador(1);
                  } else {
                    setContador(contador + 1);
                  }
                }}
              >
                Cambiar de avatar
              </button>
            </div>

            <div className={style.containerInfo}>
              <div className={style.containerInfoV}>
                <label htmlFor="name">
                  Nombre
                  <input
                    className={style.inputInformation}
                    value={upperCase(props.user.name)}
                    id="name"
                  ></input>
                  <button
                    onClick={(e) => editAtribute(e, "name")}
                    className="editButton"
                  >
                    Editar
                  </button>
                </label>

                <label htmlFor="">
                  Apellido
                  <input
                    className={style.inputInformation}
                    value={upperCase(props.user.lastName)}
                  ></input>
                  <button
                    onClick={(e) => editAtribute(e, "lastName")}
                    className="editButton"
                  >
                    Editar
                  </button>
                </label>

                <label htmlFor="">
                  Telefono
                  <input
                    className={style.inputInformation}
                    value={props.user.phone}
                  ></input>
                  <button
                    onClick={(e) => editAtribute(e, "phone")}
                    className="editButton"
                  >
                    Editar
                  </button>
                </label>
              </div>
              <div className={style.containerInfoV}>
                <label htmlFor="">
                  DNI
                  <input
                    className={style.inputInformation}
                    value={props.user.dni}
                  ></input>
                  <button
                    onClick={(e) => editAtribute(e, "dni")}
                    className="editButton"
                  >
                    Editar
                  </button>
                </label>
                <label htmlFor="">
                  ALIAS
                  <input
                    className={style.inputInformation}
                    value={props.user.alias.toUpperCase()}
                  ></input>
                  <button
                    onClick={(e) => editAtribute(e, "alias")}
                    className="editButton"
                  >
                    Editar
                  </button>
                </label>
                <label htmlFor="">
                  CBU
                  <input
                    className={style.inputInformation}
                    value={props.user.cbu}
                  ></input>
                  <button onClick={editAtribute} className="editButtonDisabled">
                    No puedes editar tu cbu.
                  </button>
                </label>
              </div>
            </div>
          </div>
        </section>
      ) : (
        <div className="spinner">
          <SpinnerCircular />
        </div>
      )}
    </>
  );
}

export default Profile;
