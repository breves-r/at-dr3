FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/chefe-service-0.0.1-SNAPSHOT.jar /app/chefe-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/chefe-service.jar"]
