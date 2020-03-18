FROM gradle:jdk8 as gradle
COPY build.gradle /final/
COPY settings.gradle /final/

COPY src /final/src/
WORKDIR /final/
RUN gradle build

FROM openjdk:8-jdk-alpine
WORKDIR /final/
COPY --from=gradle /final/build/libs/todo-0.0.1-SNAPSHOT.jar /final/

ENTRYPOINT ["java","-jar","todo-0.0.1-SNAPSHOT.jar"]
