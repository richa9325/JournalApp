# --- Stage 1: Build the app with Maven ---
FROM maven:3.8.5-openjdk-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# --- Stage 2: Run only the JAR in a slim JDK image ---
FROM openjdk:21.0.1-jdk-slim

WORKDIR /app

COPY --from=build /app/target/journalApp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
