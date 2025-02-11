FROM ubuntu:20.04
RUN apt-get update && \
    apt-get install -y wget curl unzip maven openjdk-17-jdk netcat && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH
RUN echo $JAVA_HOME
RUN java -version
WORKDIR /app
COPY . /app
COPY .env /app/.env
RUN export $(grep -v '^#' /app/.env | xargs) && \
    echo "APP_NAME=$APP_NAME" && \
    mvn clean install -DskipTests
RUN ls /app/target

CMD ["java", "-jar", "/app/target/app-0.0.1-SNAPSHOT.jar"]