// vite.config.js

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    middleware: [
      (req, res, next) => {
        // Permitir solicitudes desde cualquier origen
        res.setHeader("Access-Control-Allow-Origin", "*");
        // Permitir solicitudes con los siguientes métodos
        res.setHeader("Access-Control-Allow-Methods", "GET,DELETE,PATCH,POST,PUT,OPTIONS");
        // Permitir los siguientes encabezados en las solicitudes
        res.setHeader("Access-Control-Allow-Headers", "X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version, Authorization, Access-Control-Allow-Origin, Access-Control-Allow-Headers, Origin, X-Requested-With");

        // Habilitar CORS para solicitudes OPTIONS (preflight)
        if (req.method === 'OPTIONS') {
          res.writeHead(200);
          res.end();
          return;
        }

        next();
      },
    ],
  },
  // Otras configuraciones de Vite si las tienes
});
