import ProgressBar from "@ramonak/react-progress-bar";


function ProgressBarComponent(props) {
	return (
		<div className="progress-bar">
			<ProgressBar completed={props.percentage} />
		</div>
	);
}

export default ProgressBarComponent