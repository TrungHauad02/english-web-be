# create .jar file
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# create docker image
FROM openjdk:18-slim
EXPOSE 8080
COPY --from=build /home/app/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]