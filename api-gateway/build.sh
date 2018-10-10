#!/usr/bin/env bash

mvn clean compile package

docker build -t api-gateway:latest .