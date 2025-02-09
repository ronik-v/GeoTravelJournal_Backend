FROM ghcr.io/graalvm/graalvm-ce:latest
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . /app
ENV JAVA_HOME=/opt/graalvm-ce-22.3.0
ENV PATH=$JAVA_HOME/bin:$PATH
RUN mvn clean install

CMD ["java", "-jar", "target/app.jar"]