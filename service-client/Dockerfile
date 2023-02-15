FROM openjdk:8-jdk-alpine
MAINTAINER victor.fernandez1006@gmail.com
COPY ./target/service-client-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8002
ENTRYPOINT [ "java", "-jar", "app.jar" ]