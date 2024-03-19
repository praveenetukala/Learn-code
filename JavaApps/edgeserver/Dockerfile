FROM adoptopenjdk/openjdk14-openj9:alpine-jre as builder
LABEL maintainer="ctel"
LABEL build_date="2021-02-01"
WORKDIR application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
#### Add NON-ROOT User #####
RUN addgroup bpclgroup && adduser  --ingroup bpclgroup --disabled-password bpclctel
USER bpclctel
FROM adoptopenjdk/openjdk14-openj9:alpine-jre
WORKDIR application
COPY --from=builder application/dependencies/ ./
#### START OF TIMEZONE RELATED COMMANDS ####
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/Asia/Kolkata /etc/localtime
RUN true
COPY --from=builder application/spring-boot-loader/ ./
RUN true
COPY --from=builder application/snapshot-dependencies/ ./
RUN true
COPY --from=builder application/application/ ./
RUN true
CMD [ "java", "org.springframework.boot.loader.JarLauncher" ]