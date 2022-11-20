FROM java:8
VOLUME /tmp
ADD huahuo-ffpo-user-gateway-1.0-SNAPSHOT.jar /app-gateway.jar
EXPOSE 51601
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app-gateway.jar"]