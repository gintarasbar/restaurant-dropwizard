package com.ft.restaurants.resources;

import com.codahale.metrics.annotation.Timed;
import com.ft.restaurants.domain.*;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
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

    public RestaurantResource() {

    }

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

    @GET
    @Path("/find-by-name/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRestaurantsByName(@PathParam("name") String name) {
        Set<Restaurant> foundRestaurants = restaurantRepository.findRestaurantsByName(restaurantRepository.getRestaurants(), name);
        return Response
                .status(Response.Status.FOUND)
                .entity(foundRestaurants)
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/distance")
    public Response getRestaurantDistance(@PathParam("id") UUID id, DistanceRequest distanceRequest) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        Location restaurantLocation = new Location(restaurant.getLongitude(), restaurant.getLatitude());
        Location searchLocation = new Location(distanceRequest.getLongitude(), distanceRequest.getLatitude());
        Distance distance = new Distance(restaurantLocation, searchLocation);
        return Response
                .status(Response.Status.OK)
                .entity(distance)
                .build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // TODO: Use builder for constructing/copying restaurant object
    // TODO: Create restaurantrequest object
    public Response addRestaurant(RestaurantRequest request) {
        checkNotNull(request);
        Restaurant newRestaurant = restaurantService.createRestaurant(request);
        return Response
                .status(Response.Status.CREATED)
                .entity(newRestaurant)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/update-restaurant")
    public Response updateRestaurant(@PathParam("id") UUID id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        checkNotNull(restaurant);
        checkArgument(id.equals(restaurant.getId()),"ids must be equal");
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant, restaurantRequest);
        restaurant = updatedRestaurant;
        return Response
                .status(Response.Status.OK)
                .entity(restaurant)
                .build();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello world";
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
}
