package com.ft.restaurants.resources;

import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;

// TODO: Build the restaurant resource jersey test
@RunWith(MockitoJUnitRunner.class)
public class RestaurantResourceTest {
    public static final String RESTAURANTS_V1 = "/restaurants/v1";
    public static final UUID RESTAURANT_ID = UUID.randomUUID();
    public static final String RESTAURANT_NAME = "restaurant-name";
    /*public static final String RESTAURANT_NAME = "restaurant-name";
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
                                                    .build();*/

    @Mock
    private static RestaurantService restaurantService;

    @Mock
    private static RestaurantRepository restaurantRepository;

    // TODO: look jersey tests in redeem token svc or acc-licence-svc

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new RestaurantResource(restaurantService))
            .build();

    @Before
    public void setup() {
        resources.getObjectMapper()
                .registerModule(new JodaModule());
    }


    @After
    public void onTeardown() { reset(restaurantService); }

    @Test
    public void shouldReturn200WhenGetRestaurantByIdIsCalledAndReturnRestaurant() {
        Restaurant restaurant = RestaurantBuilder.restaurantBuilder().id(RESTAURANT_ID).name(RESTAURANT_NAME).build();
        given(restaurantService.findRestaurantById(RESTAURANT_ID)).willReturn(Optional.of(restaurant));

        Response response = resources.client()
                .target(RESTAURANTS_V1)
                .path("/")
                .path(RESTAURANT_ID.toString())
                .request(MediaType.APPLICATION_JSON)
                .get();

        assertThat(response.getStatus(), is(HttpStatus.OK_200));
        assertThat(response.readEntity(Restaurant.class), is(restaurant));
    }
}
