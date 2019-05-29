#!/usr/bin/env bash
port=7777
docker stop notification-service
docker rm notification-service
docker rmi notification-service
docker run -i -d -p 7777:7777 --expose 7777 --name notification-service -t demo/notification-service
