# spring-cloud-movie-recommendation
Spring Cloud Microservices - Movie Recommendation System

The application includes the following microservices.

 * Eureka Discovery Service
 * Config Service
 * User Service
 * Movie Service
 * Recommendation Service
 * Recommendation Client Service
 
This reference application uses the following mixture of databases.

* MySQL - RDBMS
* Neo4j - GraphDB
* MongoDB - Document Store
## Postman

Use Postman to make requests. Collection of all requests are exported and can be found in root of this repository.

## Config Service

Configuration service MUST be the first service to be started, only after that, other services can be started.
To run configuration service, open terminal in root directory and execute:

```mvn spring-boot:run```

It will run on __localhost:8888__. This service connects to [this] (https://github.com/mdeket/spring-cloud-example-config-repo) github repository
and loads property files for other services. 

## Eureka Discovery Service

Discovery service is the one of key tenents of a microservice based architecture. Eureka is the Netflix Service Discovery Server and Client.
To run this service, do the same as for Config Service, open terminal, position it to discovery service root directory and execute:


```mvn spring-boot:run```

Eureka starts on localhost:8761.

## User Service

Contains all users for this system. They are all stored in UserSOA database. Before starting user service, you must have MySQL installed and running on localhost:3306 with UserSOA database created.
All other db properties are stored [here](https://github.com/mdeket/spring-cloud-example-config-repo/blob/master/user-service-default.yml)
To run user service, do the same as for the last two services.

By starting the app, spring runs data.sql script with 50 users, with names and emails. Script can be found in resource folder.
App will run on localhost:8001.

## Movie Service

Is in charge of movies. Before starting this service, just be sure to have installed and a running mongodb instance on default port.
To populate database, run __Movie Service - Dummy Data__ request from postman collection you imported.
To start movie service, do the same as for last three services, just position yourself inside of movie service project :grin: .

## Recommendation Service

Is in charge of recommendation logic. Its database contains basic information about users(id) and movies(id), 
and as well as information about which user liked which movie and who is user following.
Neo4j must be installed in order to run this service. After running this service (localhost:9000) and execution of Dummy Data request from postman,
you can see all the nodes and paths in Neo4j Graph Visualization that runs on localhost:7474.

If user for which recommendation is requested is not following anybody, he/she will get top 5 movies based on likes.

## Recommendation Client

Represents an API Gateaway for all services. It receives http requests from user's browser and then it passes request to other services.
It uses ribbon for client side load balancing, so for example two services of recommendation service can be up, and ribbon will take take which instance to use on each request. To be able to do that, 
you have to change configuration of recommendation service so you could be able to run instance twice on different ports.
Recommendation client uses circiut breaker (hystrix). So if recommendation service is down or movie service is down, it has 5 default movies that will be used as recommendation.
