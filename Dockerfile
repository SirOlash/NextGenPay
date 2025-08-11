# Use Java 21 as base image
FROM eclipse-temurin:21-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the project files from the correct location
COPY java_backend/pom.xml .
COPY java_backend/src ./src

# Build the application
RUN --mount=type=cache,target=/root/.m2 ./mvnw clean package -DskipTests

# Use JRE for the final image to reduce size
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/*.jar NextGenPay.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "NextGenPay.jar"]
