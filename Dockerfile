FROM maven:3.9.12-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./
RUN ./mvnw dependency:go-offline

COPY src src
RUN chmod +x mvnw && ./mvnw dependency:go-offline

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=build /app/target/BeautyAgenda-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
