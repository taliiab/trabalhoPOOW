
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /usuario-tarefa

RUN git clone https://github.com/taliiab/trabalhoPOOW.git

RUN mvn clean package -DskipTests

FROM quay.io/wildfly/wildfly:36.0.1.Final-jdk21

COPY --from=build /usuario-tarefa/target/usuario_tarefa.war /opt/jboss/wildfly/standalone/deployments/

