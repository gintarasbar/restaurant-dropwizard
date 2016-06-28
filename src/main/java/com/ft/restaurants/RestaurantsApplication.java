package com.ft.restaurants;

import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.resources.RestaurantResource;
import com.ft.restaurants.service.RestaurantService;
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
        RestaurantRepository restaurantRepository = new RestaurantRepository(new CSVReader(), new CSVWriter());
        RestaurantService restaurantService = new RestaurantService(restaurantRepository);
        environment.jersey().register(new RestaurantResource(restaurantService));
        try {
            restaurantRepository.loadData();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
