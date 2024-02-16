import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import { PrimeReactProvider } from 'primereact/api';
import { AuthProvider } from "../src/context/AuthProvider.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <PrimeReactProvider>
    <AuthProvider>
    <App />
    </AuthProvider>
    </PrimeReactProvider>
  </React.StrictMode>,
)
