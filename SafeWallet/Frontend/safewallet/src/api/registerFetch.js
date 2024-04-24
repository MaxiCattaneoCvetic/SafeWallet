import axios from "axios";

// axios.interceptors.request.use((config) => {
//   // Configurar encabezados CORS
//   config.headers["Access-Control-Allow-Origin"] = "http://localhost:9090";
//   // Otros encabezados según sea necesario
//   return config;
// });

async function fetchKeyc(datos, url) {
  try {
    const response = await axios.post(url, datos);
    return response;
    
  } catch (error) {
    console.error('Error en fetchKeyc:', error);
    throw error;
  }
}



async function fetchuserFull(datos, url) {
  // eslint-disable-next-line no-useless-catch
  try{
    const response =  await axios.post(url,datos);
    return response;
  }catch(error){
    throw error
  }

}

export { fetchKeyc, fetchuserFull };
