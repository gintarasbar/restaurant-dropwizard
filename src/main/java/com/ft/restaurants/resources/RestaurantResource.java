package com.ft.restaurants.resources;

import com.codahale.metrics.annotation.Timed;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantRequest;
import com.ft.restaurants.domain.TagRequest;
import com.ft.restaurants.service.RestaurantService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jorge on 6/16/2016.
 */


@Path(RestaurantResource.RESTAURANTS_V1)
public class RestaurantResource {
    public static final String RESTAURANTS_V1 = "/restaurants/v1";
    private final RestaurantService restaurantService;

    public RestaurantResource(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    public Response getRestaurantById(@PathParam("id") UUID id) {
        Optional<Restaurant> existingRestaurant = restaurantService.findRestaurantById(id);
        if (existingRestaurant.isPresent()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(existingRestaurant.get())
                    .build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterRestaurants(@QueryParam("longitude") Double longitude, @QueryParam("latitude") Double latitude, @QueryParam("distance") Double distance, @QueryParam("name") String name) {
        List<Restaurant> filteredRestaurant = restaurantService.filterByName(restaurantService.getAllRestaurants(), name);
        filteredRestaurant = restaurantService.filterByDistance(filteredRestaurant, longitude, latitude, distance);
//        Location currentLocation = new Location(longitude, latitude);
//        Set<Restaurant> foundRestaurants = new HashSet<>();
//        for (Restaurant restaurant: restaurantService.getRestaurants()) {
//            Location restaurantLocation = new Location(restaurant.getLongitude(), restaurant.getLatitude());
//            Distance restaurantDistance = new Distance(currentLocation, restaurantLocation);
//            if (restaurantDistance.getDistance() <= distance ) {
//                foundRestaurants.add(restaurant);
//            }
//        }
        return Response
                .status(Response.Status.FOUND)
                .entity(filteredRestaurant.subList(0,Math.min(20,filteredRestaurant.size())))
                .build();
    }

    @GET
    @Path("save")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCSV() {
        restaurantService.saveCSV();
        return Response
                .status(Response.Status.OK)
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
    public Response updateRestaurant(@PathParam("id") UUID id, Restaurant restaurantRequest) {
        checkNotNull(restaurantRequest);
        checkArgument(id.equals(restaurantRequest.getId()),"Restaurant Ids do not match");
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(id);
        if (restaurant.isPresent()) {
            checkArgument(id.equals(restaurant.get().getId()), "ids must be equal");
            Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurant.get(), restaurantRequest);
            return Response
                    .status(Response.Status.OK)
                    .entity(updatedRestaurant)
                    .build();
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/add-tag")
    public Response addTag(@PathParam("id") UUID id, TagRequest tag) {
        checkNotNull(tag);
        Optional<Restaurant> restaurant = restaurantService.findRestaurantById(id);
        if (restaurant.isPresent()) {
            checkArgument(id.equals(restaurant.get().getId()), "ids must be equal");
            Restaurant taggedRestaurant = restaurantService.tagRestaurant(restaurant.get(), tag);
            return Response
                    .status(Response.Status.OK)
                    .entity(taggedRestaurant)
                    .build();
        }
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

}
