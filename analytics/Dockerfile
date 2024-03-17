FROM openjdk:17-alpine

EXPOSE 8110

VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
