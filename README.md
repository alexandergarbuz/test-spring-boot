# :house: Spring Boot Test Project

Test project for learning Spring Boot. It provides a simple overview of SpringBoot micro service that is integrated with a database layer using JPA and Hibernate.

It also provides additional functionality like Swagger and Actuator healthcheck.

## Requirements

* Java 21 or later
* Maven 3.8.4 or later
* Spring Boot 3.5.7 or later


## Overview


The entry point to this application is `HelloWorldController`. It provides a simple implementation of a REST micro service with various end points.

One of the end points provides a simple example of integraiton with H2 database.

* `HelloWorldConroller.displayHelloClass` method provides an example of an end point that provides response as complex Java object.
* `HelloService` is a simple implementation of a business logic layer that acts as `facade` for business logic components and data layer.
* `MessageDao` is a simple DAO object that provides access to information stored in the database.
* `application.properties` - main configuration file.
* `schema.sql` - a script that will be executed during application start up and contains DDL for the database tables.
* `data.sql` - a script that will be executed after database was created to insert the data into the tables.
* `index.html` - starting page for the application.

## Accessing Application

By default the application will be available at [http://localhost](http://localhost)