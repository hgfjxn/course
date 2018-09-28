#!/usr/bin/env bash

thrift --gen java -out ../src/main/java ./UserService.thrift

thrift --gen java -out ../../user-service/src/main/java ./UserService.thrift