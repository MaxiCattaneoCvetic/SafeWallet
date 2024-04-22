// eslint-disable-next-line no-undef
import formatNum from "format-num";
function Test() {
	let value= 10015151515115000;
	return (
		<div style={{ textAlign: "center" }}>
			<h1>test</h1>
			<p>$ {formatNum("1000000")}</p>
			<input
			value={formatNum(value)}
			style={{ width: "100px", color: "red" }}>
			</input>
		</div>
	);
}

export default Test


