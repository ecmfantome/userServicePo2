FROM openjdk:21-jdk
WORKDIR /home/api/
COPY ./target/*.jar ./app.jar
CMD [ "java", "-jar", "app.jar" ]