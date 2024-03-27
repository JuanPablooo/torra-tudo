FROM openjdk:17-oracle
LABEL matainer="juanpablopereiragomes@hotmail.com"

EXPOSE 8080

WORKDIR /opt/torra/gateway

COPY target/gateway.jar gateway.jar


CMD java -jar gateway.jar