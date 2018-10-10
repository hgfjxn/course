#!/usr/bin/env bash

mvn clean compile package

docker build -t user-service:latest .