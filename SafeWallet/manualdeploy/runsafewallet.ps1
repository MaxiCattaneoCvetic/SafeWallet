Write-Host "Bienvenido a Safe Wallet 😎 - Proyecto hecho por Maximiliano Cattaneo Cvetic"
Write-Host "Visita mi perfil en LinkedIn 🚀 ----> https://www.linkedin.com/in/mcvetic/"
Write-Host "Presiona Enter para continuar..."
$null = Read-Host

Write-Host "- Descargando imágenes docker necesarias para el proyecto..."

docker pull mcvetic97/sw-userfull:v1
docker pull mcvetic97/sw-transfers:v1
docker pull mcvetic97/sw-keycloack:v1
docker pull mcvetic97/sw-gateway:v1
docker pull mcvetic97/sw-server:v1
docker pull mcvetic97/sw-exportpdf:v1

Write-Host "-------------------------------------------------------------"
Write-Host "😎😎-------Imágenes descargadas----------😎😎"
Write-Host "Ya tenemos las imágenes descargadas, ahora vamos a ejecutar el script para hacer el despliegue..."
Write-Host "Presiona Enter para continuar..."
$null = Read-Host
Write-Host "Si el script no funciona, ejecuta manualmente el comando: sudo chmod 777 docker-compose.yml y luego docker-compose up -d"
Write-Host "Ejecutando el script....."


Write-Host "Descargando docker-compose.yml..."
Invoke-WebRequest -Uri "https://raw.githubusercontent.com/MaxiCattaneoCvetic/runsafewallet/main/docker-compose.yml" -OutFile "docker-compose.yml"


Write-Host "Ejecutando el script....."
docker-compose up -d


Write-Host "El script se ejecutó con éxito. ¡¡ATENCIÓN!! La aplicación puede tardar unos minutos en levantarse."
Write-Host "Recuerda seguir las instrucciones para levantar el frontend y poder utilizar safewallet"
Write-Host "Usuario admin: admin@admin.com | Contraseña: admin | CBU: 5069815974733830697771"
Write-Host "Usuario user: user@user.com | Contraseña: user | CBU: 6513591138974673569357"
