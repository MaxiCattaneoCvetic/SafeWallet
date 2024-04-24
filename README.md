# Safe Wallet

SafeWallet es una billetera virtual que ofrece una experiencia segura y conveniente para administrar tus finanzas. Desde crear una cuenta hasta enviar dinero a otros usuarios, SafeWallet te brinda las herramientas necesarias para manejar tus transacciones de manera eficiente y protegida.

Este proyecto se enfoca en la implementación de un robusto esquema de microservicios y medidas de seguridad utilizando Keycloak, demostrando así mis habilidades en el desarrollo de aplicaciones escalables y seguras.


## 🛠 Tecnologias del proyecto
- Java-Springboot 
- React
- MongoDB
- Keycloak
- Javascript
- Aws 
- Docker

## Microservicios

Safe Wallet consta de 6 microservicios que trabajan de manera sincronizada para garantizar el óptimo funcionamiento de la aplicación:

- **Config-Server**: Almacena la configuración de los microservicios de forma integrada, ofreciendo facilidad al administrador para gestionar y modificar la configuración según sea necesario.
- **Gateway**: Actúa como el guía principal de la aplicación, recibiendo las peticiones del cliente (Frontend) y direccionándolas hacia los microservicios correspondientes. Controla el acceso a las peticiones desde puntos definidos, asegurando un flujo controlado y seguro.
- **Keycloak**: Garantiza la seguridad de los usuarios en la plataforma, almacenando y gestionando sus credenciales de acceso. Desde la autenticación hasta el almacenamiento seguro de la información del usuario, Keycloak desempeña un papel fundamental en la protección de los datos.
- **Server**: Eureka Server, encargado de la gestión y registro de los microservicios, facilitando su integración y funcionamiento coordinado.
- **Transfers**: Gestiona todas las transferencias de fondos, manteniendo un historial detallado de las transacciones y las tarjetas asociadas a cada usuario. Esencial para garantizar la integridad y la trazabilidad de las operaciones financieras.
- **UserDataFull**: En colaboración con Keycloak, este microservicio almacena información completa del usuario, excluyendo la contraseña. Actúa como un nexo crucial entre los diferentes microservicios, facilitando la comunicación y la integración de datos a través de Feign.
- **ExportPdf**: Este microservicio es el encargado de generar el pdf de las transacciónes.

## Estructura del proyecto.
![image](https://github.com/MaxiCattaneoCvetic/SafeWallet/assets/101187172/ed2bcc21-3e7f-45f3-a722-93b8aa116f37)

## Estructura del proyecto (deploy online).
![image](https://github.com/MaxiCattaneoCvetic/SafeWallet/assets/101187172/98a01024-3162-4615-9647-60c674413c1a)



## Deployment - En linea

Puedes acceder a el  deploy oficial del proyecto, si es que este se encuentra online, de lo contrario utilizar el deployment automatico.

```bash
  https://safewallet-sooty.vercel.app/
```
**USUARIOS**
```bash
  cuenta_1: admin@admin.com || admin - > CBU: 0515133297503950675439
  cuenta_2: user@user.com || user - > CBU: 1606825549683998112636
```


## Deployment - Automatico
De lo contrario puedes clonar el proyecto en la rama localhost.
Para realizar este proceso deberas tener instalado nuestro gran  amigo DOCKER Y DOCKER-COMPOSE

**Frontend** 

- Ejecutamos el front dentro de la carpeta correspondiente,  \Frontend\safewallet

**importante que se ejecute en el puerto 5173**

```bash
 npm run dev
```

**Backend**

- Linux
Ejecutamos el siguiente script
```bash
bash -c "$(curl -fsSL https://raw.githubusercontent.com/MaxiCattaneoCvetic/runsafewallet/main/runsafewallet.sh)"
```
Seguimos las indicaciones del script.
- PowerShell
Ejecutamos el siguiente script
```bash
Invoke-Expression ((New-Object System.Net.WebClient).DownloadString("https://raw.githubusercontent.com/MaxiCattaneoCvetic/runsafewallet/main/runsafewallet.ps1"))
```
Seguimos las indicaciones del script.
## Deployment - Manual
En el caso de que no funcionen los scripts o tengas algun problema podes ejecutarlos de esta manera.
```bash
1) Ingresamos a la carpeta manualdeploy.
2) Damos permisos de ejecución al script runsafewallet.sh.
3) Ejecutamos el script runsafewallet.sh para linux o runsafewallet.ps1 para powershell
3) Seguimos las indicaciones del script.
```

# Documentación de la API

Se utilizo Swagger para documentar la api, pueden encontrar la documentacion de cada microservicio siguiendo el enlace

**Microservicio: keycloack** 
deploy local: http://localhost:8081/doc/swagger-ui/index.html#/
deploy online: https://keycloack-production.up.railway.app/doc/swagger-ui/index.html#/
**Microservicio: UserDataFull**
deploy local: http://localhost:8087/doc/swagger-ui/index.html#/ 
deploy online: https://userdatafull-production.up.railway.app/doc/swagger-ui/index.html#/

**Microservicio: Transfers**
deploy local: http://localhost:8086/doc/swagger-ui/index.html#/
deploy online: https://transfers-production.up.railway.app/doc/swagger-ui/index.html#/



# 🚀🚀🚀🚀¡Muchas gracias por ver este proyecto!🚀🚀🚀🚀 
