FROM openjdk:latest
EXPOSE 8080
ADD target/timesheet-devops.jar timesheet-devops.jar
ENTRYPOINT ["java","-jar","/timesheet-devops.jar"]

