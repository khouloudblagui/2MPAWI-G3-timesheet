FROM openjdk:latest
EXPOSE 8080
ARG JAR_FILE=target/timesheet-devops.jar
ADD ${JAR_FILE} timesheet-devops.jar
ENTRYPOINT ["java","-jar","/timesheet-devops.jar"]
