import Swal from "sweetalert2";
import deleteCard from "../../../api/deleteCard";

function handleDelete(numberCard) {
  Swal.fire({
    title: "¿Estas seguro que deseas eliminar la tarjeta?",
    text: "al precionar aceptar la tarjeta se eliminara permanentemente de tu cuenta",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Si, quiero eliminarla",
  }).then((result) => {
    if (result.isConfirmed) {
      try {
        deleteCard(numberCard).then((response) => {
          if (response.status === 200) {
            Swal.fire({
              title: "¡Eliminada!",
              text: "Tu tarjeta ha sido eliminada.",
              icon: "success",
            });
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
  });
}
function DeleteCard() {
  return (
    <>
      <form action="">
        <input
          type="number"
          className="sinflecha"
          placeholder="Ingresa el numero de tu tarjeta a eliminar"
        />
        <button className="primarybtn" onClick={handleDelete}>
          Eliminar tarjeta
        </button>
      </form>
    </>
  );
}

export default DeleteCard;
