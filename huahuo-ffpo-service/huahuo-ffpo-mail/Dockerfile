FROM openjdk:8
VOLUME /tmp
ADD huahuo-ffpo-mail-1.0-SNAPSHOT.jar /app-mail.jar
EXPOSE 51906
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app-mail.jar"]