FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/*.jar tesis-app.jar
ENTRYPOINT ["java","-jar","tesis-app.jar"]