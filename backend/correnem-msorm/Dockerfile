# Dockerfile para microsserviços dependentes de correnem-msorm
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the application and install it in the local repository
RUN mvn clean package install -DskipTests
