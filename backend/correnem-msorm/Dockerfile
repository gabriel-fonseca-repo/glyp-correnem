# Dockerfile para microsserviços dependentes de correnem-msorm
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application and install it in the local repository
RUN mvn clean package install -DskipTests

# Use a lightweight JDK image to run the application
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /root/.m2 /root/.m2
COPY --from=builder /app/target/correnem-msorm-*-exec.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
