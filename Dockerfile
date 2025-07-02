FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests
RUN ls -l target/


RUN cp target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
