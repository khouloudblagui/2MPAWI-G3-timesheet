FROM openjdk:latest
EXPOSE 8080
ADD target/timesheet-devops-0.0.3-SNAPSHOT.jar timesheet-devops-0.0.3-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/timesheet-devops-0.0.3-SNAPSHOT.jar"]
