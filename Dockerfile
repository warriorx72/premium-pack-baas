FROM eclipse-temurin:21
RUN mkdir -p /app/
ADD build/libs/web-1.0.0.jar /app/web-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/app/web-1.0.0.jar"]