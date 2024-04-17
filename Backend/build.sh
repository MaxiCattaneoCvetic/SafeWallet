cd SafeWallet-ConfigServer/SafeWallet-ConfigServer
mvn clean package
docker build -t sw_image-configserver:1 .
docker push mcvetic97/configserver:sw_image-configserver:1
cd ..
cd ..

cd SafeWallet-ExportPdf
mvn clean package
docker build -t sw_image-exportpdf:1 .
docker push mcvetic97/exportpdf:sw_image-exportpdf:1
cd ..

cd SafeWallet-Server
mvn clean package
docker build -t sw_image-server:1 .
docker push mcvetic97/eurekaserver:sw_image-server:1
cd ..

cd SafeWallet-Gateway
mvn clean package
docker build -t sw_image-gateway:1 .
docker push mcvetic97/gateway:sw_image-gateway:1
cd ..

cd SafeWallet-SpringBoot-Keycloack/Safewallet-keycloak
mvn clean package
docker build -t sw_image-keycloack:1 .
docker push mcvetic97/keycloack:sw_image-keycloack:1
cd ..
cd ..

cd SafeWallet-Transfers
mvn clean package
docker build -t sw_image-transfers:1 .
docker push mcvetic97/transfers:sw_image-transfers:1
cd ..

cd SafeWallet-UserDataFull
mvn clean package
docker build -t sw_image-userfull:1 .
docker push mcvetic97/userfull:sw_image-userfull:1
cd ..
echo "-----------------------😎Build Successful😎------------------------------"
