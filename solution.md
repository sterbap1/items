## Items API
This Items REST API provides all of the required CRUD operations for the `Item`s dataset

### Build the Spring-boot web app
Run the following command to build the WAR with Maven:
```
mvn clean install
```
Ensure the final artifact `./target/items-web.war` exists

This WAR can be run directly via `java -jar -Dserver.port=8082 ./target/items-web.war`

### Build Docker image
Run the following command to build a local Docker image:
```
docker build --tag items:latest .
```

### Run Docker image
Run the following command to run the Docker container

Modifications:
* Setting `INTERNAL_WEB_PORT` will set the value the Spring Boot Web container runs on inside of the Docker container
* Setting `EXTERNAL_WEB_PORT` will set the value the user can visit the web-app from a browser or curl 

```
export INTERNAL_WEB_PORT=8080
export EXTERNAL_WEB_PORT=8082
docker run --env WEB_PORT=${INTERNAL_WEB_PORT} -p ${EXTERNAL_WEB_PORT}:${INTERNAL_WEB_PORT} items:latest
```

### Use the API
Via browser or curl, visit `http://localhost:${EXTERNAL_WEB_PORT}/items`

#### Examples:

* Set the entire list of `Item`s:
  * `curl --header "Content-Type: application/json" --request POST --data '[{"name": "item1"}, {"name": "item2"}]' http://localhost:8082/items`
* Add an `Item` with name "test":
  * `curl --header "Content-Type: application/json" --request PUT --data '[{"name": "test"}]' http://localhost:8082/items`
* Retrieve all `Item`s:
  * `curl --request GET http://localhost:8082/items`
* Delete all `Item`s:
  * `curl --request DELETE http://localhost:8082/items`