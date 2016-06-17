package com.ft.restaurants;

import com.ft.restaurants.resources.RestaurantResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by Jorge on 6/16/2016.
 */
public class RestaurantsApplication extends Application<RestaurantsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new RestaurantsApplication().run(args);
    }

    @Override
    public String getName() {
        return "Restaurants";
    }

    @Override
    public void initialize(final Bootstrap<RestaurantsConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final RestaurantsConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(
                new RestaurantResource()
        );
    }
}
