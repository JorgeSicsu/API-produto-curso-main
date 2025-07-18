# Usa imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Cria volume para arquivos temporários
VOLUME /tmp

# Copia o .jar do build Maven (ajuste o nome se for fixo)
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expõe a porta 80 para o Azure
EXPOSE 80

# Força o Spring Boot a escutar na porta 80
ENTRYPOINT ["java", "-Dserver.port=80", "-jar", "/app.jar"]
