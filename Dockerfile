FROM openjdk:14-alpine
MAINTAINER Sharipov
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ARG JAR_FILE=/target/distance-study-platform-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

