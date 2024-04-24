echo "Bienvenido a Safe Wallet 😎 - Proyecto hecho por Maximiliano Cattaneo Cvetic"
echo "Visita mi perfil en linkedin 🚀 ----> https://www.linkedin.com/in/mcvetic/"
echo "Pulsa enter para continuar..."
read ENTER
echo "- Descargando imagenes docker necesarias para el proyecto... "

docker pull mcvetic97/sw-userfull:v1
docker pull mcvetic97/sw-transfers:v1
docker pull mcvetic97/sw-keycloack:v1
docker pull mcvetic97/sw-gateway:v1
docker pull mcvetic97/sw-server:v1
docker pull mcvetic97/sw-exportpdf:v1


echo "-------------------------------------------------------------"
echo "😎😎-------Imagenes descargadas----------😎😎"
echo "Ya tenemos las imagenes descargadas, ahora vamos a ejecutar el script para hacer el despliegue..."
echo "Pulsa enter para continuar..."
read ENTER
echo "Si el script no funciona ejecutar manualmente el comando: sudo chmod 777 docker-compose.yml y luego docker-compose up -d "
echo "Ejecutando el script....."
docker-compose up -d
echo "El script se ejecuto con exito ¡¡ATENCIÓN!!! la aplicación puede tardar unos minutos en levantarse.."
echo "Recuerda seguir las instrucciones para levantar el frontend y poder utilizar safewallet"
echo "Usuario admin: admin@admin.com Password: admin | CBU: 5069815974733830697771"
echo "Usuario user: user@user.com Password: user | CBU: 6513591138974673569357 "

