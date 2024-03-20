FROM openjdk:17-oracle
LABEL matainer="juanpablopereiragomes@hotmail.com"

EXPOSE 8080

WORKDIR /opt/torra/discovery

COPY target/discovery*.jar discovery.jar


CMD java -jar discovery.jar