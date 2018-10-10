#!/usr/bin/env bash


mvn clean package

docker build -t course-service:latest .


# test docker image
# docker run -it course-service --zookeeper.address=10.20.13.24 --mysql.address=10.20.13.24 --mysql.port=3306