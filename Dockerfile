FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar neurotech-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "neurotech-api.jar"]
