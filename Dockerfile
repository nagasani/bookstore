FROM eclipse-temurin:17-jdk
WORKDIR /opt
COPY target/*.jar /opt/app.jar
ENTRYPOINT ["java", "$JAVA_OPTS", "-jar", "app.jar"]
