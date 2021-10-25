###  STAGE 1: Build step ###
FROM amazoncorretto:11 as build

WORKDIR /workspace

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle lombok.config docker-entrypoint.sh ./
COPY src src
COPY lib lib
COPY config config

RUN ./gradlew build
RUN mkdir -p dist && cp build/libs/* dist/
RUN cp docker-entrypoint.sh dist/

### STAGE 2: Application image  ###
FROM amazoncorretto:11-alpine-jdk

WORKDIR /
#VOLUME /tmp

COPY --from=build /workspace/dist/* ./

ENTRYPOINT ["/bin/sh", "docker-entrypoint.sh"]
