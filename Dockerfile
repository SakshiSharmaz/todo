# syntax=docker/dockerfile:experimental
FROM gradle:jdk8 as gradle
RUN --mount=type=cache,id=gradle,target=/home/gradle/.gradle gradle build --no-daemon
COPY build.gradle /final/
COPY settings.gradle /final/

COPY src /final/src/
WORKDIR /final/
RUN gradle build

FROM openjdk:8-jdk-alpine
WORKDIR /final/
COPY --from=gradle /final/build/libs/todo-0.0.1-SNAPSHOT.jar /final/

ENTRYPOINT ["java","-jar","todo-0.0.1-SNAPSHOT.jar"]
