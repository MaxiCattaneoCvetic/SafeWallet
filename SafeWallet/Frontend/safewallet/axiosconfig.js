import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:9090', 
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*', // Configura el origen permitido según tu configuración de CORS
    },
});

export default instance;
