FROM openjdk:17-jdk-alpine

EXPOSE 8081

COPY target/springAuthorization-0.0.1-SNAPSHOT.jar devapp.jar

CMD ["java", "-jar", "/devapp.jar"]