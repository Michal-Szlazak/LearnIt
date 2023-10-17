FROM openjdk:17-jdk-slim AS build

COPY ./db/pom.xml ./db/mvnw ./
COPY ./db/.mvn .mvn
RUN ./mvnw dependency:resolve

COPY ./db/src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/*.jar demo.jar
ENTRYPOINT ["java", "-jar", "demo.jar"]

# Expose the port your Spring Boot app will run on
EXPOSE 8083

# Define the command to run your application
CMD ["java", "-jar", "demo.jar"]
