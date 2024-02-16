import React, { createContext, useState, useContext, useEffect } from 'react';
import Keycloak from "keycloak-js";

// Creamos un contexto para el estado de autenticación
const AuthContext = createContext();

// Hook personalizado para acceder al contexto de autenticación
export function useAuth() {
  return useContext(AuthContext);
}

// Proveedor de contexto para envolver toda la aplicación
export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [authenticated, setAuthenticated] = useState(false);
  const [username, setUsername] = useState('');


  const login = () => {
    const keycloak = new Keycloak({
      url: "http://localhost:8080",
      realm: "safewallet",
      clientId: "frontend_client",
    });
    
    const authenticated = keycloak
    .init({
      onLoad: "login-required",
      redirectUri: "http://localhost:5173",
    })

    console.log(authenticated);
    // Aquí puedes realizar la lógica de inicio de sesión y establecer el usuario
    setUser(keycloak.token);

  };

  const logout = () => {
    // Aquí puedes realizar la lógica de cierre de sesión y eliminar el usuario
    setUser(null);
  };
  


  


  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );









}
