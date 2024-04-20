#descargamos la imagen base del jdk

FROM eclipse-temurin:17
LABEL author="Safe Wallet"

# En este paso lo que hacemos es copiar un conjunto de archivos de mi maquina host a mi contenedor
COPY safewallet-0.0.1-SNAPSHOT.jar sw-keycloak.jar

#Ejecutamos el jar
ENTRYPOINT ["java", "-jar", "sw-keycloak.jar"]
