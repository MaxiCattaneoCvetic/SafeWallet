// import React, { createContext, useState, useContext, useEffect } from 'react';
// import keylockInit from  "../security/keyloack.js"

// // Creamos un contexto para el estado de autenticación
// const AuthContext = createContext();

// // Hook personalizado para acceder al contexto de autenticación
// export function useAuth() {
//   return useContext(AuthContext);
// }

// // Proveedor de contexto para envolver toda la aplicación
// export function AuthProvider({ children }) {
//   const [user, setUser] = useState("");
//   const [authenticated, setAuthenticated] = useState(false);
//   const [username, setUsername] = useState('');


//   async function  login()  {
//     keylockInit();
//   }

//   const logout = () => {
//     // Aquí puedes realizar la lógica de cierre de sesión y eliminar el usuario
//     setUser(null);
//   };
  


  


//   return (
//     <AuthContext.Provider value={{ user, login, logout }}>
//       {children}
//     </AuthContext.Provider>
//   );









// }
