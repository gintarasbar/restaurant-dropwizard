package com.ft.restaurants.resources;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.ft.restaurants.domain.CreateRestaurantRequest;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jorge on 6/16/2016.
 */


@Path(RestaurantResource.RESTAURANTS_V1)
public class RestaurantResource {
    public static final String RESTAURANTS_V1 = "/restaurants/v1";
    private RestaurantService restaurantService = new RestaurantService();
    private RestaurantRepository restaurantRepository = new RestaurantRepository();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response get(@PathParam("id") UUID id) {
        Restaurant existingRestaurant = restaurantRepository.findRestaurantById(id);
        if (existingRestaurant == null) {
            // TODO: Return optional empty if not found
            // Optional<Restaurant> empty = Optional.empty();
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.FOUND)
                .entity(existingRestaurant)
                .build();
    }

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public List<Restaurant> getAllRestaurants() {

        return restaurantService.getRestaurants();
    }*/

    /*@GET
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
    }*/

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // TODO: Use builder for constructing/copying restaurant object
    // TODO: Create restaurantrequest object
    public Response addRestaurant(CreateRestaurantRequest request) {
        checkNotNull(request);
        Restaurant newRestaurant = restaurantService.createRestaurant(request);
        return Response
                .status(Response.Status.CREATED)
                .entity(newRestaurant)
                .build();
    }

    @PUT
    @Path("{id}/update-restaurant")
    @Timed
    @ExceptionMetered
    public Restaurant updateRestaurant(@PathParam("id") UUID id, Restaurant restaurant) {
        checkNotNull(restaurant);
        checkArgument(id.equals(restaurant.getId()),"ids must be equal");
        Restaurant existingRestaurant = restaurantService.updateRestaurant(restaurant);
        return existingRestaurant;
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
