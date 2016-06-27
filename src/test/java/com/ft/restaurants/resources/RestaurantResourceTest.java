package com.ft.restaurants.resources;

import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

// TODO: Build the restaurant resource jersey test
@RunWith(MockitoJUnitRunner.class)
public class RestaurantResourceTest {
    public static final String RESTAURANTS_V1 = "/restaurants/v1";
    public static final UUID RESTAURANT_ID = UUID.randomUUID();
    public static final String RESTAURANT_NAME = "restaurant-name";
    public static final String RESTAURANT_TAG = "restaurant-tag";
    public static final String RESTAURANT_ADDRESS = "restaurant-address";
    public static final String RESTAURANT_CITY = "restaurant-city";
    public static final String RESTAURANT_POSTCODE = "restaurant-postcode";
    public static final Integer RESTAURANT_HYGIENE_RATING = 1;
    public static final Location RESTAURANT_LOCATION = new Location(0.0, 0.0);






    public static final Restaurant RESTAURANT = RestaurantBuilder
                                                    .restaurantBuilder()
                                                    .id(RESTAURANT_ID)
                                                    .name(RESTAURANT_NAME)
                                                    .tag(RESTAURANT_TAG)
                                                    .address(RESTAURANT_ADDRESS)
                                                    .city(RESTAURANT_CITY)
                                                    .postcode(RESTAURANT_POSTCODE)
                                                    .hygieneRating(RESTAURANT_HYGIENE_RATING)
                                                    .location(RESTAURANT_LOCATION)
                                                    .build();

    @Mock
    RestaurantService restaurantService;

    @Mock
    RestaurantRepository restaurantRepository;

// TODO: look jersey tests in redeem token svc or acc-licence-svc
//    @ClassRule
//    public static final ResourceTestRule RULE
//            =  ResourceTestRule.builder()
//            .addResource(new RestaurantResource(restaurantService, restaurantRepository))
//            .build();
}
