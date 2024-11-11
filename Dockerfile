FROM gradle:8.10.2-jdk23-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
WORKDIR /
COPY settings.gradle .
COPY build.gradle .
COPY gradle/ gradle/
COPY src/ src/
COPY build/ build/
RUN gradle bootJar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar build/libs/backend-0.0.1-SNAPSHOT.jar
