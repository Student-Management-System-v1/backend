## Two Stages build
FROM maven:3.8.6-amazoncorretto-17
WORKDIR /
COPY . .
RUN mvn clean install -DskipTests

ADD ./target/Student_Management_System-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080

## One Stage build
#From openjdk:17
#ADD ./target/Student_Management_System*.jar demo.jar
#ENTRYPOINT ["java", "-jar" , "demo.jar"]
#EXPOSE 8080