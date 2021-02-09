
### Required
Java 8+
Maven 3+

### Run 
mvn -N io.takari:maven:wrapper
./mvnw package && java -jar target/transport_api.jar

### Docker
docker build -t springio/transport_api .
docker run -p 8080:8080 springio/transport_api


### To run on development mode:
create a application-dev.properties file 
on resources directory like application-dev-example.properties

### Database: 
From db folder import transport.sql file into Mysql
