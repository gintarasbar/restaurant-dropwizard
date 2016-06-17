package com.ft.restaurants.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Jorge on 6/16/2016.
 */

@Path("/hello")
public class RestaurantResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting() {
        return "Hello world";
    }
}
