# Stage 1
FROM maven:3.6.0-jdk-11-slim AS build
COPY src ./src
COPY pom.xml .
RUN mvn -f ./pom.xml clean validate compile test package

# Stage 2
FROM openjdk:11-jdk-buster
ADD target/silent-eight-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar silent-eight-app-0.0.1-SNAPSHOT.jar
