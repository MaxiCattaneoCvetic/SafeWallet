import swal from "sweetalert";
function SwalModal() {

	return(
		swal("Write something here:", {
			content: "input",
		})
		.then((value) => {
			swal(`You typed: ${value}`);
		})
	)
}

export default SwalModal