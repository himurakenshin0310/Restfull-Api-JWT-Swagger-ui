#define base docker file.
FROM openjdk:11
LABEL maintainer="springboot"
COPY target/springboot-project1-0.0.1-SNAPSHOT.jar springboot-project.jar
ENTRYPOINT ["java", "-jar", "springboot-project.jar"]	