FROM openjdk:8
VOLUME /tmp
ADD huahuo-ffpo-shop-1.0-SNAPSHOT.jar /app-shop.jar
EXPOSE 51608
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app-shop.jar"]