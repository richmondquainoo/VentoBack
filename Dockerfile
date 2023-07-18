FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/devops.jar devops.jar

ENTRYPOINT ["java", "-jar", "/devops.jar"]

FROM mysql:latest
COPY init.sql /docker-entrypoint-initdb.d/
