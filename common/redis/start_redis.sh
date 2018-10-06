#!/bin/bash

docker run -itd  -p 6379:6379 -v `pwd`/data:/data --name redis_dev  redis:latest
