# Dockerfile para microsserviços dependentes de correnem-msorm
FROM maven:3.9.6-eclipse-temurin-21 as builder
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

# Instalar dependências necessárias
RUN apt-get update && apt-get install -y \
  apt-transport-https \
  ca-certificates \
  gnupg \
  curl

RUN ls -al /

# Adicionar a chave do Google Cloud SDK
RUN echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | \
  tee -a /etc/apt/sources.list.d/google-cloud-sdk.list && \
  curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | \
  gpg --no-tty --batch --dearmor --yes -o /usr/share/keyrings/cloud.google.gpg && \
  apt-get update -y && apt-get install google-cloud-sdk -y

ENV GOOGLE_APPLICATION_CREDENTIALS=/correnem/gcloud-service-account-key.json
RUN gcloud auth activate-service-account --key-file=/correnem/gcloud-service-account-key.json
RUN gcloud init

# Limpar o cache do apt
RUN apt-get clean && rm -rf /var/lib/apt/lists/*

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
