# Construire l'image
# docker build -f src/main/docker/Dockerfile.jvm.multi . -t sihm/spring-demo-jvm:1.0

FROM openjdk:11.0.5-jdk-stretch AS build
WORKDIR /workdir/server
COPY .mvn /workdir/server/.mvn
COPY pom.xml /workdir/server/pom.xml
COPY mvnw /workdir/server/mvnw
RUN ./mvnw dependency:go-offline

COPY src/main/java /workdir/server/src/main/java
COPY src/main/resources /workdir/server/src/main/resources
RUN ./mvnw clean package -DskipTests
RUN mkdir -p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar

FROM openjdk:11.0.5-jre-slim-buster
EXPOSE 8080
VOLUME /tmp
ARG DEPENDENCY=/workdir/server/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.lg.demo.DemoApplication"]