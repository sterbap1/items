FROM openjdk:8-jdk-alpine

ARG WAR_NAME=items-web.war
ARG TARGET_WAR=target/${WAR_NAME}
ARG WEB_DIR=/
# WAR_PATH needs to be ENV to be available for variable substitution in shell
ENV WAR_PATH=${WEB_DIR}${WAR_NAME}
ENV WEB_PORT=3000

COPY ${TARGET_WAR} ${WAR_PATH}
EXPOSE ${WEB_PORT}

# cannot substitute Docker arguments directly into ENTRYPOINT via exec form, so use `sh -c`
ENTRYPOINT ["sh", "-c", "java -jar -Dserver.port=${WEB_PORT} ${WAR_PATH}"]