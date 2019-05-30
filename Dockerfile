FROM openjdk:8-jdk-alpine
RUN mkdir -p /usr/app
COPY ./notification-service-1.0.0-SNAPSHOT-exec.jar /usr/app
WORKDIR /usr/app
EXPOSE 7777
ENTRYPOINT ["java", "-jar", "notification-service-1.0.0-SNAPSHOT-exec.jar"]