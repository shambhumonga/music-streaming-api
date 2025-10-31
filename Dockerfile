FROM openjdk:25-jdk

WORKDIR /app

ADD target/music-api.jar api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api.jar"]