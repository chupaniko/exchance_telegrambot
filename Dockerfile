FROM adoptopenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=custdev_exchance_bot
ENV BOT_TOKEN=2132800663:AAHCvZ56KmcfcUBTf1_URvSrhOrSjzIS-DE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]
