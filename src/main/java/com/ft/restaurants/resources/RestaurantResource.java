package com.ft.restaurants.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.ft.restaurants.domain.CreateRestaurantRequest;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.service.RestaurantService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jorge on 6/16/2016.
 */


@Path(RestaurantResource.RESTAURANTS_V1)
public class RestaurantResource {
    public static final String RESTAURANTS_V1 = "/restaurants/v1";
    private RestaurantService restaurantService;

    @Inject
    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = checkNotNull(restaurantService);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Restaurant get(@PathParam("id") UUID id) {
        Restaurant existingRestaurant = restaurantService.findRestaurantById(id);
        if (existingRestaurant == null) {
            // TODO: Return optional empty if not found
            //Optional<Restaurant> empty = Optional.empty();
        }
        return existingRestaurant;
    }

    @POST
    @Timed
    @ExceptionMetered
    // TODO: Use builder for constructing/copying restaurant object
    // TODO: Create restaurantrequest object
    public Restaurant addRestaurant(@Valid CreateRestaurantRequest request) {
        Restaurant newRestaurant = Restaurant.copy(request)
                .id(UUID.randomUUID())
        .build();
        restaurantService.createRestaurant();

        return newRestaurant;
    }

    @PUT
    @Timed
    @ExceptionMetered
    public Restaurant updateRestaurant(@PathParam("id") UUID id, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantService.findRestaurantById(id);
        System.out.println();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello world";
    }

    public RestaurantResource() {

    }
}
