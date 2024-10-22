FROM maven:3.9.7-eclipse-temurin-17-alpine AS build

WORKDIR /app
COPY . /app

RUN [ "mvn", "clean", "package" ]

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/task-worker/target/task-worker-0.0.1-SNAPSHOT.jar task-worker-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["java", "-jar", "task-worker-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081