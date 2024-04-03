# Safe Wallet

SafeWallet es una billetera virutal, la cual permite crear una cuenta, ingresar tarjetas y enviar dinero a otros usuarios de la plataforma.

Este proyecto busca implementar un esquema de microservicios y seguridad con Keycloak y demostrar mis habilidades.


# Microservicios

Safe Wallet posee 6 microservicios que trabajan de manera conjunta para cumplir con el optimo funcionamiento de la app.

**Config-Server**: Destinado a almacenar la configuración de los microservicios de manera integrada y proporcionar facilidad al administrador para cambiar la configuración.
**Gateway**: Es nuestro guia, este microservicio toma las peticiones del cliente (Front) y las redirige a cada microservicio. Controla que las peticiones se hagan desde un punto de acceso definido.
**Keycloak**: Encargado de la seguridad de los usuarios en nuestra aplicación, se encarga de almacenar el usuario y clave de nuestros usuarios, asi como tambien de su autenticación.
**Server**: Eureka Server, se encarga de gestionar nuestros microservicios, permitiendo su registro integrado.
**Transfers**: Encargado de gestionar todas las transferencias, historial de transferencias y tarjetas de nuestros usuarios.
**UserDataFull**: Trabaja en conjunto con Keycloack, pero este microservicio almacena la informacion completa del usuario, menos su contraseña ya que es indiferente para este microservicio. Es uno de los mas importantes ya que funciona como nexo de otros microservicios a traves de feign.
