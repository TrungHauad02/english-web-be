# create .jar file
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/englishweb/english_web_be/EnglishWebApplication.java /home/app/src/main/java/com/englishweb/english_web_be/EnglishWebApplication.java

RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

# create docker image
FROM openjdk:18-slim
EXPOSE 8080
COPY --from=build /home/app/target/*.jar /app.jar

# Copy configuration files directly into the container
COPY ./src/main/resources/englishweb-firebase-adminsdk.json /app/src/main/resources//englishweb-firebase-adminsdk.json
COPY ./src/main/resources/englishweb-speech-to-text.json /app/src/main/resources/englishweb-speech-to-text.json

# Set environment variables for the JSON configuration paths
ENV FIREBASE_CONFIG_PATH=/app/src/main/resources/englishweb-firebase-adminsdk.json
ENV SPEECH_TO_TEXT_CONFIG_PATH=/app/src/main/resources/englishweb-speech-to-text.json

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]