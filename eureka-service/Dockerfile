# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR do servidor Eureka para o contêiner
COPY target/eureka-service-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Exponha a porta 8761 para acessar o Eureka Server
EXPOSE 8761

# Comando para rodar o Eureka Server
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]
