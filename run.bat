@echo off
setlocal

set "DB_CONTAINER_NAME=my-mongodb"
set "JAR_FILE=.\app\target\BetterTogether-1.0-SNAPSHOT.jar"

start "Docker Compose" cmd /k "docker-compose up"
start "Java Application" java -jar "%JAR_FILE%"
