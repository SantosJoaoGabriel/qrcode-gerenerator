FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-tmurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

ARG AWS_ACESS_KEY_ID
ARG AWS_SECRET_ACESS_AWS_ACESS_KEY_ID

ENV AWS_REGION=us-east-1
ENV AWS_BUCKET_NAME=generated-code

ENTRYPOINT ["java", "-jar", "app.jar"]