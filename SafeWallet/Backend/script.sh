#!/bin/bash

# Iniciar el primer microservicio (configserver)
docker-compose -f docker-compose.yml up -d configserver

sleep 30
# Esperar a que el primer microservicio esté completamente activo (opcional)
# Puedes usar un comando como sleep para esperar un tiempo determinado antes de iniciar el siguiente microservicio.
# Ejemplo:
# sleep 30  # Esperar 30 segundos (ajusta el tiempo según sea necesario)

# Iniciar el segundo microservicio (safewalletiam)
docker-compose -f docker-compose.yml up -d safewalletiam

sleep 40
# Iniciar el tercer microservicio (eurekaserver)
docker-compose -f docker-compose.yml up -d eurekaserver
sleep 30
# Iniciar el cuarto microservicio (gateway)
docker-compose -f docker-compose.yml up -d gateway
sleep 30
# Iniciar el quinto microservicio (exportpdf)
docker-compose -f docker-compose.yml up -d exportpdf
sleep 30
# Iniciar el sexto microservicio (keycloack)
docker-compose -f docker-compose.yml up -d keycloack
sleep 30
# Iniciar el séptimo microservicio (userdatafull)
docker-compose -f docker-compose.yml up -d userdatafull
sleep 30
# Iniciar el octavo microservicio (transfers)
docker-compose -f docker-compose.yml up -d transfers
