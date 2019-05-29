FROM localhost:5000/demo-base-image
VOLUME /tmp
COPY config /config/
RUN ls -la /config/*
ADD notification-service-1.0.0-SNAPSHOT-exec.jar notification-service.jar
RUN bash -c 'touch /notification-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-service.jar", "--spring.config.location=/config/deployment.docker.yml", "--logging.config=/config/logback-spring.xml"]