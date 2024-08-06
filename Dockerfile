FROM openjdk:17-jdk
LABEL authors="ckr"
COPY build/libs/*.jar app.jar
EXPOSE 8088

CMD ["java", "-jar", "app.jar"]