FROM eclipse-temurin:17-jdk-alpine
WORKDIR /courseApp
COPY ./target/course-service-app.jar /courseApp
CMD ["java", "-jar", "course-service-app.jar"]
EXPOSE 8082