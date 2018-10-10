#!/usr/bin/env bash

mvn clean package

docker build -t course-edge-service:latest .

# test docker image
# docker run -it course-edge-service:latest --zookeeper.address=10.20.13.24