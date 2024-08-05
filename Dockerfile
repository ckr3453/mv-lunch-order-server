FROM openjdk:17-jdk
LABEL authors="ckr"
COPY build/libs/lunch-order-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8088

CMD ["java", "-jar", "app.jar"]