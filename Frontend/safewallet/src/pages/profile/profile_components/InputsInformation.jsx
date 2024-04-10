import style from "../profile.module.css";
import upperCase from "../../../functions/upperCase.js";
import Swal from "sweetalert2";
import getUserData from "../../../api/getUserData.js";
import patchUserEdit from "../../../api/patchUserEdit.js";
import { useNavigate } from "react-router-dom";
import { updateEmail } from "../../../functions/updateEmail.js";

function InputsInformation(props) {
  const navigate = useNavigate();

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
      inputPlaceholder: `Ingresa el nuevo dato...`,
      preConfirm: async (newValue) => {
        try {
          const dataToUpdate = {
            [field]: newValue,
          };
          const response = await patchUserEdit(props.user.id, dataToUpdate);
          switch (response.status) {
            case 200:
              if (field === "email") {
                getUserData(dataToUpdate.email).then(() => {
                  updateEmail();
                });
                return;
              }
              getUserData(props.user.email);
              break;
            case 406:
              throw new Error(response.response.data);
            case 500:
              throw new Error("Error al actualizar la información");
            default:
              throw new Error("Error al actualizar la información");
          }
        } catch (error) {
          if (error.response.status === 401) {
            Swal.fire("Tu sesion ha caducado", "Por favor inicia sesión");
            sessionStorage.clear();
            navigate("/");
          }
          Swal.showValidationMessage(`Request failed: ${error.response.data}`);
        }
      },
      allowOutsideClick: () => !Swal.isLoading(),
    }).then((result) => {
      if (result.isConfirmed) {
        let timerInterval;
        Swal.fire({
          title: "Estamos actualizando tus datos!",
          html: "Esta ventana se cerrará en <b></b> milisegundos.",
          timer: 1000,
          timerProgressBar: true,
          didOpen: () => {
            Swal.showLoading();
            const timer = Swal.getPopup().querySelector("b");
            timerInterval = setInterval(() => {
              timer.textContent = `${Swal.getTimerLeft()}`;
            }, 100);
          },
          willClose: () => {
            clearInterval(timerInterval);
            location.reload();
          },
        }).then((result) => {
          if (result.dismiss === Swal.DismissReason.timer) {
            console.log("I was closed by the timer");
          }
        });
      }
    });
  }
  return (
    <>
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
            <button className="editButtonDisabled">
              No puedes editar tu cvu.
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
            <button className="editButtonDisabled">
              No puedes editar tu cbu.
            </button>
          </label>
        </div>
        <div className={style.containerInfoV}>
          <label htmlFor="">
            Correo
            <input
              className={style.inputInformation}
              value={props.user.email}
            ></input>
            <button
              onClick={(e) => editAtribute(e, "email")}
              className="editButton"
            >
              Editar
            </button>
          </label>
          <label htmlFor="">
            CVU
            <input
              className={style.inputInformation}
              value={props.user.cvu}
            ></input>
            <button className="editButtonDisabled">
              No puedes editar tu cvu.
            </button>
          </label>
        </div>
      </div>
    </>
  );
}

export default InputsInformation;
