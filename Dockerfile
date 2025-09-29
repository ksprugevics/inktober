# Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot JAR file into the container
COPY target/inktober-1.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8081

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
