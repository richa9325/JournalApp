# --- Stage 1: Build the app with Maven ---
FROM maven:3.8.5-openjdk-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# --- Stage 2: Use a valid Java 21 JDK slim image ---
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
