DB_CONTAINER_NAME := my-mongodb
JAR_FILE := ./app/target/BetterTogether-1.0-SNAPSHOT.jar

.PHONY: run-mongo stop-mongo

# Start the MongoDB Docker container and Java application
run: run-java run-mongo

build:
	docker-compose up --build

# Run your Java application
run-java:
	java -jar $(JAR_FILE) &

# Start the MongoDB Docker container
run-mongo:
	docker-compose up

# Stop the MongoDB Docker container
stop:
	docker-compose down