# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
#FROM adoptopenjdk:17-jre-hotspot
#FROM eclipse-temurin:17-jre

#FROM postgres:13
#
#RUN apt-get update && apt-get install -y postgresql-client



# Set the working directory inside the container
WORKDIR /app

# Copy the current directory contents into the container
COPY target/files-management-0.0.1-SNAPSHOT.jar /app/files-management.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "files-management.jar"]




