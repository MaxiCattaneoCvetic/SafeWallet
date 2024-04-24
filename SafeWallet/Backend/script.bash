#!/bin/bash

docker-compose up configserver -f
docker-compose up safewalletiam up -f
# Continuar con los otros servicios en el orden deseado...
