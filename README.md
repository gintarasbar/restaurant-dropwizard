# restaurant-dropwizard
Dropwizard project example for Restaurant search

# Restaurant Service Readme #

### What is restaurant service? ###
* This is a drop wizard application that offers a service to search, add and update restaurants with different criteria.

### Setup ###
* Summary
    * Download Postman Chrome extension
    * Run RestaurantService in your local IDE
    * Connect to localhost:8080/restaurants/v1

## Using the service (JSON request and respond body specified in RAML)
* Run/init RestaurantApplication and connect to localhost:8080

* Get all restaurants
    GET localhost:8080/restaurants/v1
* Get restaurant by id
    GET localhost:8080/restaurants/v1/{id}
* Filter restaurants by different criteria
    GET localhost:8080/restaurants/v1?name=test&longitude=1.0&latitude=1.0&distance=1.0
* Add new restaurants to the repository
    POST localhost:8080/restaurants/v1/
* Update a restaurant in the repository
    PUT localhost:8080/restaurants/v1/{id}/update-restaurant
* Save changes to CSV file
    GET localhost:8080/restaurants/v1/save

### Implementation Constraints ###
* Java 8
* Dropwizard 0.7.1
