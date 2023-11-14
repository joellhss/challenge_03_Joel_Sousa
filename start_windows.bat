@echo off

cd /d ./ms-user
echo "Building the user microservice"
mvn clean install -DskipTests
cd ..

cd /d ./ms-notification
echo "Building the notification microservice"
mvn clean install -DskipTests
cd ..

echo "Running docker composer"
docker-compose up -d

echo "Application built and executed successfully!"