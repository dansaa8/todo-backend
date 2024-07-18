# Use the Ubuntu-based JRE image from Canonical
FROM openjdk:21

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at /app
COPY target/todo-backend-0.0.1-SNAPSHOT.jar /app/todo-backend.jar

# Specify the command to run your application
CMD ["java", "-jar", "/app/todo-backend.jar"]
