# ----------------------------------------------------
# Stage 1: Build the application
# ----------------------------------------------------
# Using Maven image to build the JAR so you don't have to run 'mvn package' manually
FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Skip tests to speed up the build in this context
RUN mvn clean package -DskipTests

# ----------------------------------------------------
# Stage 2: Run the application
# ----------------------------------------------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy only the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]