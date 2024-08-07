#FROM openjdk:17-jdk
#LABEL authors="ckr3453@github.com"
#COPY ./dependencies/ ./
#COPY ./spring-boot-loader/ ./
#COPY ./snapshot-dependencies/ ./
#COPY ./application/ ./
#ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "org.springframework.boot.loader.JarLauncher"]

FROM openjdk:17-jdk
LABEL authors="ckr3453@github.com"
COPY ./build/libs/*.jar app.jar
EXPOSE 8088
CMD ["java", "-jar", "app.jar"]