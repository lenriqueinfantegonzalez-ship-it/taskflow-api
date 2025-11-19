# Usa una imagen base de Java optimizada para Spring Boot
FROM eclipse-temurin:21-jdk-alpine

# **LÍNEA AÑADIDA PARA ROMPER LA CACHÉ**
# Esto fuerza a Docker a ignorar la caché de las capas subsiguientes cada vez.
ARG CACHE_BREAKER=1.0

# Argumento para el nombre del JAR (usamos un comodín para capturar el nombre con la versión)
ARG JAR_FILE=target/*.jar

# Copia el JAR compilado desde el host al contenedor, llamándolo 'app.jar'
COPY ${JAR_FILE} app.jar

# Expone el puerto por defecto de la API (8081 según tu docker-compose)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]