# Use a Gradle image to build the application
FROM gradle:8-jdk21 AS builder

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copy the application source code
COPY src src

# Grant execution permission to the Gradle wrapper
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Use a minimal JDK image for running the application
FROM gcr.io/distroless/java21-debian11

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
