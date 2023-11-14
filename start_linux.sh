#!/bin/bash

if ! systemctl status docker | grep -q "Active: active (running)"; then
  echo "Docker is not running. Starting..."
  systemctl start docker
fi

cd ./ms-user
echo "Building the user microservice"
mvn clean install -DskipTests
cd ..

cd ./ms-notification
echo "Building the notification microservice"
mvn clean install -DskipTests
cd ..

echo "Running docker composer"
docker-compose up

echo "Application built and executed successfully!"
