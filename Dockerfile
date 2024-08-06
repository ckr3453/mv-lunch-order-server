FROM openjdk:17-jdk
LABEL authors="ckr3453@github.com"
ARG JAR_NAME="lunch-order-0.0.1-SNAPSHOT"
WORKDIR app
COPY ./${JAR_NAME}/dependencies ./
COPY ./${JAR_NAME}/spring-boot-loader ./
COPY ./${JAR_NAME}/snapshot-dependencies ./
COPY ./${JAR_NAME}/application ./
EXPOSE 8088/tcp
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "org.springframework.boot.loader.JarLauncher"]