FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="dominika.czycz@gmail.com"
VOLUME /main-app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]