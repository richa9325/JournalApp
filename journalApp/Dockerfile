# --- Stage 1: Build the app with Maven ---
FROM maven:3.8.5-openjdk-21 AS build

# Set working directory inside builder container
WORKDIR /app

# Copy project files into the container
COPY . .

# Package the Spring Boot application (skip tests to speed up build)
RUN mvn clean package -DskipTests

# --- Stage 2: Create a lightweight image with just the jar ---
FROM openjdk:21.0.1-jdk-slim

# Set working directory inside final image
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port your app listens to
EXPOSE 8081

# Command to run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
