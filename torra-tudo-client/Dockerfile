FROM openjdk:11

ARG PROFILE

ARG ADD_OPT

ENV PERFIL=${PROFILE}

ENV ADDICTIONAL_OPT=${ADD_OPT}

WORKDIR /opt/client

COPY /target/client*.jar client.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080
EXPOSE 5050
EXPOSE 5432
#CMD echo teste
CMD java ${ADDICTIONAL_OPT} -jar client.jar --spring.profiles.active=${PERFIL}
