FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/appproduto-*.jar appproduto.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-jar", "/appproduto.jar"]