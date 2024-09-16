FROM openjdk:8
EXPOSE 8080
ADD target/banking-docker.jar banking-docker.jar
ENTRYPOINT ["java","-jar","/banking-docker.jar"]