FROM openjdk:8-jre

ENV ACTIVE_PROFILE prod

COPY wms.jar /app/wms.jar

CMD ["/usr/bin/java", "-jar", "/app/wms.jar" , "--spring.profiles.active=${ACTIVE_PROFILE}"]