FROM openjdk:8-jdk-alpine

RUN apk add maven

WORKDIR /opt/

COPY . .

RUN mvn clean install -Dmaven.test.skip=true

RUN ls -lht target

ENTRYPOINT ["java","-jar","target/converter-0.0.1-SNAPSHOT.jar"]