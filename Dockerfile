# Use Java 21 as base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the project files from the correct location
COPY java_backend/pom.xml ./pom.xml
COPY java_backend/src ./src/

# Build the application
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests


# Use JRE for the final image to reduce size
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/*.jar NextGenPay.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "NextGenPay.jar"]
