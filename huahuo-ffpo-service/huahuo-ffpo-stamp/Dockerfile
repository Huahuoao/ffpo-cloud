FROM openjdk:8
VOLUME /tmp
ADD huahuo-ffpo-stamp-1.0-SNAPSHOT.jar /app-stamp.jar
EXPOSE 51804
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app-stamp.jar"]