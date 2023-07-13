FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/devops-integration.jar devops-integration.jar

ENTRYPOINT ["java", "-jar", "/devops-integration.jar"]