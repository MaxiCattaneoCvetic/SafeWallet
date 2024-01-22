import axios from "axios";

axios.interceptors.request.use((config) => {
  // Configurar encabezados CORS
  config.headers["Access-Control-Allow-Origin"] = "http://localhost:3000";
  // Otros encabezados según sea necesario
  return config;
});

async function fetchKeyc(datos, url) {
  try {
    const response = await axios.post(url, datos).then((res)=>{
      console.log(res);
    });
    return response;
  } catch (error) {
    console.error('Error en fetchKeyc:', error);
    throw error;
  }
}


// await fetch(url, {
// 	method: 'POST',
// 	headers: {
// 		mode: 'no-cors',
// 		'Access-Control-Allow-Origin': '*',
// 		'Content-Type': 'application/json',
// 	},
// 	body: JSON.stringify(datos),
// }).then((response)=> console.log(response,"asdasdas")).then((data) =>{
// 	console.log(data);
// })

async function fetchuserFull(datos, url) {
  await axios
    .post(url, datos)
    .then(function (response) {
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
}

export { fetchKeyc, fetchuserFull };
