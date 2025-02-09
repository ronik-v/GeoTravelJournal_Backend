FROM ghcr.io/graalvm/graalvm-ce:latest
WORKDIR /app
COPY target/*.jar app.jar
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

CMD ["java", "-jar", "app.jar"]