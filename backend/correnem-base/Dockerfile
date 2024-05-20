# Dockerfile para microsserviços dependentes de correnem-msorm
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Copy the local Maven repository from correnem-msorm
COPY --from=glyp-msorm:latest /root/.m2 /root/.m2

# Build the application
RUN mvn clean package -DskipTests

# Use a lightweight JDK image to run the application
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
