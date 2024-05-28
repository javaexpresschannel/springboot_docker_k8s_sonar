FROM openjdk:17-jdk-slim
ADD target/springboot_docker_k8s_sonar-0.0.1-SNAPSHOT.jar springboot_docker_k8s_sonar-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","springboot_docker_k8s_sonar-0.0.1-SNAPSHOT.jar"]

