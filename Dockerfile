FROM openjdk:latest
#FROM gradle:latest
#CMD "chmod +x gradlew"
#RUN "./gradlew build"
ARG JAR_FILE=./build/libs/my-security-site-0.1.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9090/tcp
ENTRYPOINT ["java","-jar","/app.jar"]
