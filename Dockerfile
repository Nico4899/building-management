FROM openjdk:latest
EXPOSE 8080
ADD target/building-management.jar building-management.jar
ENTRYPOINT ["java","-jar","building-management.jar"]