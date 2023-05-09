FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar sample.jar
ENTRYPOINT ["java","-jar","/sample.jar"]
