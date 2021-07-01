#!/bin/bash

mvn clean install

if [[ ! -f "./target/items-web.war" ]]
then
  echo "maven build failed, exiting"
  exit -1
fi

export DOCKER_IMAGE=items:latest
docker build --tag ${DOCKER_IMAGE} .

export INTERNAL_WEB_PORT=8080
export EXTERNAL_WEB_PORT=8082
echo "visit http://localhost:${EXTERNAL_WEB_PORT}/items"

docker run --env WEB_PORT=${INTERNAL_WEB_PORT} -p ${EXTERNAL_WEB_PORT}:${INTERNAL_WEB_PORT} ${DOCKER_IMAGE}