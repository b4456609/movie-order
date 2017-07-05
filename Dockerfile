FROM openjdk:8-alpine

WORKDIR /opt/app/

EXPOSE 8080

COPY ./build/libs/order-0.0.1-SNAPSHOT.jar /opt/app/

ENTRYPOINT exec java $JAVA_OPTS -jar order-0.0.1-SNAPSHOT.jar