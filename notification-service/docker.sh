#!/usr/bin/env bash
port=7777
docker stop notification-service
docker rm notification-service
docker rmi notification-service
docker image build -t notification-service ~/Notification-service/.
docker run -d -p 7778:7777 --name notification-service notification-service