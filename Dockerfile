# Install nori
FROM docker.elastic.co/elasticsearch/elasticsearch:8.13.4
RUN elasticsearch-plugin install analysis-nori

# Build stage
FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Package stage
FROM openjdk:17
COPY --from=build /home/gradle/src/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
