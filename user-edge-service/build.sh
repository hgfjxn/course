#!/usr/bin/env bash

mvn clean compile package

docker build -t user-edge-service:latest .