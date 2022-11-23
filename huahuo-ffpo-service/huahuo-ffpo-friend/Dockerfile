FROM java:8
VOLUME /tmp
ADD huahuo-ffpo-friend-1.0-SNAPSHOT.jar /app-friend.jar
EXPOSE 51909
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app-friend.jar"]