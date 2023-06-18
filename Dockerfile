FROM openjdk:17-alpine
COPY ./build/libs/dr-server-0.0.1-SNAPSHOT.jar /build/libs/dr-server-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/build/libs/dr-server-0.0.1-SNAPSHOT.jar"]