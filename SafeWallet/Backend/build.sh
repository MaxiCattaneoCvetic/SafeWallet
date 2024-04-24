# cd SafeWallet-ConfigServer/SafeWallet-ConfigServer
# mvn clean package
# docker build -t sw_image-configserver .
# docker tag sw_image-configserver mcvetic97/sw-configserver:v1
# docker push mcvetic97/sw-configserver:v1
# cd ..
# cd ..

cd SafeWallet-ExportPdf
mvn clean package
docker build -t sw_image-exportpdf .
docker tag sw_image-exportpdf mcvetic97/sw-exportpdf:v1
docker push mcvetic97/sw-exportpdf:v1
cd ..

cd SafeWallet-Server
mvn clean package
docker build -t sw_image-server .
docker tag sw_image-server mcvetic97/sw-server:v1
docker push mcvetic97/sw-server:v1
cd ..

cd SafeWallet-Gateway
mvn clean package
docker build -t sw_image-gateway .
docker tag sw_image-gateway mcvetic97/sw-gateway:v1
docker push mcvetic97/sw-gateway:v1
cd ..

cd SafeWallet-SpringBoot-Keycloack/Safewallet-keycloak
mvn clean package
docker build -t sw_image-keycloack .
docker tag sw_image-keycloack mcvetic97/sw-keycloack:v1
docker push mcvetic97/sw-keycloack:v1
cd ..
cd ..

cd SafeWallet-Transfers
mvn clean package
docker build -t sw_image-transfers .
docker tag sw_image-transfers mcvetic97/sw-transfers:v1
docker push mcvetic97/sw-transfers:v1
cd ..

cd SafeWallet-UserDataFull
mvn clean package
docker build -t sw_image-userfull .
docker tag sw_image-userfull mcvetic97/sw-userfull:v1
docker push mcvetic97/sw-userfull:v1
cd ..
echo "-----------------------😎Build Successful😎------------------------------"



 
 

