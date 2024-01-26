FROM openjdk:8

COPY target/*.jar api-gateway.jar

ENTRYPOINT ["java","-jar","/api-gateway.jar"]