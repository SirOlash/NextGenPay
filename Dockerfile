#FROM ubuntu:latest
#LABEL authors="DELL"
#
#ENTRYPOINT ["top", "-b"]

# Use Java 11 as the base image
FROM eclipse-temurin:11-jdk-focal

# Set working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the project files
COPY pom.xml .
COPY src ./src

# Make the mvnw script executable
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Use the slim JRE for the final image to reduce size
FROM eclipse-temurin:11-jre-focal

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/*.jar NextGenPay.jar

# Expose the port your application runs on (typically 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "NextGenPay.jar"]
