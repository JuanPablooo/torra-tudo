FROM openjdk:17-oracle
LABEL matainer="juanpablopereiragomes@hotmail.com"

EXPOSE 8080

WORKDIR /opt/torra/torra-tudo-config-server

COPY target/config-server*.jar config-server.jar


CMD java -jar config-server.jar