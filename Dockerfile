# Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy jar file into container
COPY target/*.jar app.jar

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]