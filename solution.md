## Items API
This Items REST API provides all of the required CRUD operations for the `Item`s dataset

Note: The following commands are all run with the `buildAndRun.sh` file in one convenient shell script

### Build the Spring-boot web app
Run the following command to build the WAR with Maven:
```
mvn clean install
```
Ensure the final artifact `./target/items-web.war` exists

This WAR can be run directly via `java -jar -Dserver.port=3000 ./target/items-web.war`

### Build Docker image
Run the following command to build a local Docker image using `Dockerfile`:
```
docker build --tag items:latest .
```

### Run Docker image
Run the following command to run the Docker container

Modifications:
* Setting `INTERNAL_WEB_PORT` will set the value the Spring Boot Web container runs on inside of the Docker container
* Setting `EXTERNAL_WEB_PORT` will set the value the user can visit the web-app from a browser or curl 

```
export INTERNAL_WEB_PORT=3000
export EXTERNAL_WEB_PORT=3000
docker run --env WEB_PORT=${INTERNAL_WEB_PORT} -p ${EXTERNAL_WEB_PORT}:${INTERNAL_WEB_PORT} items:latest
```

### Using the API
Via browser or curl, visit `http://localhost:${EXTERNAL_WEB_PORT}/items`

### Managing all Items
Managing all Items in the dataset
#### Examples:

* Set the entire list of `Item`s:
  * `curl --header "Content-Type: application/json" --request POST --data '[{"name": "item1"}, {"name": "item2"}]' http://localhost:3000/items`
* Add two `Item`s with name "test" and "item1":
  * `curl --header "Content-Type: application/json" --request PUT --data '[{"name": "test"}, {"name": "item1"}]' http://localhost:3000/items`
* Retrieve all `Item`s:
  * `curl --request GET http://localhost:3000/items`
* Delete all `Item`s:
  * `curl --request DELETE http://localhost:3000/items`
  
### Managing Items by ID
One can also manage individual Items by their UUID and standard REST Path variables
#### Examples:

* Get an `Item` by its ID:
  * `curl --request GET http://localhost:3000/items/eb17ec99-74f7-4f74-b105-6e34df3df3b0`
* Update an `Item` by its ID with a new name:
  * `curl --request PUT http://localhost:3000/items/eb17ec99-74f7-4f74-b105-6e34df3df3b0/newNameValue`
* Delete an `Item` by its ID:
  * `curl --request DELETE http://localhost:3000/items/eb17ec99-74f7-4f74-b105-6e34df3df3b0`
  
### Future Improvements
* Add Postgres or MongoDB persistent datastore via Docker container
  * Place web service and persistent storage in a docker-swarm or kubernetes cluster so that the datastore is not exposed publicly, using internal networking to protect the data store
  * https://docs.docker.com/samples/postgresql_service/
  * https://www.bmc.com/blogs/mongodb-docker-container/
* Add better Logging for errors/output to user, including responses to the REST API
* UNIT TESTS, especially on the service and database level
* Better JavaDocs
* Better error handling