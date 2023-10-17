DB_CONTAINER_NAME := my-mongodb

.PHONY: run-mongo stop-mongo

# Start the MongoDB Docker container
run-mongo:
	docker run -d --name $(DB_CONTAINER_NAME) -p 27017:27017 mongo

# Stop the MongoDB Docker container
stop-mongo:
	docker stop $(DB_CONTAINER_NAME)
	docker rm $(DB_CONTAINER_NAME)