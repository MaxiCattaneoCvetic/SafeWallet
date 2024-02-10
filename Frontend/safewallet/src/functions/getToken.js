
function getToken() {
	const urlParams = new URLSearchParams(window.location.search);
	const accessToken = urlParams.get('access_token');
	console.log(accessToken);
	return accessToken;
}



export default getToken;