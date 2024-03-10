FROM openjdk:17-alpine

EXPOSE 8090

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
