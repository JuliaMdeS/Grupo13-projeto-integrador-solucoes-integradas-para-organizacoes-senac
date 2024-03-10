FROM openjdk:17

WORKDIR /app

COPY ./target/api-0.0.1-SNAPSHOT.jar /app/spring-api.jar

ENTRYPOINT ["java", "-jar", "/app/spring-api.jar"]