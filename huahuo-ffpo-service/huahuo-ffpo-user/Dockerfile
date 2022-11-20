FROM openjdk:8
VOLUME /tmp
ADD huahuo-ffpo-user-1.0-SNAPSHOT.jar /ffpo-user.jar
EXPOSE 51806
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/ffpo-user.jar"]