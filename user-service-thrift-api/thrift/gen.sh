#!/usr/bin/env bash

thrift --gen java -out ../src/main/java ./UserService.thrift
