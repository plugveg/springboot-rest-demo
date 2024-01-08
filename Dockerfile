FROM eclipse-temurin:17-jre-alpine

COPY springboot-rest-demo-config/src/main/resources/springboot-rest-demo.yml /springboot/springboot-rest-demo.yml
COPY springboot-rest-demo-ws/target/springboot-rest-demo-ws-1.0.0-SNAPSHOT.jar /springboot/mon-app.jar

WORKDIR /springboot

EXPOSE 8080

CMD ["java", "-jar", "mon-app.jar"]
