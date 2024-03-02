FROM openjdk:17-alpine

EXPOSE 8080

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
