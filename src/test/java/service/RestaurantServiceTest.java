package service;

import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantRequest;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.*;

/**
 * Created by Jorge on 6/23/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository repository;

    @Before
    public void setUp() throws Exception {
        restaurantService = new RestaurantService(repository);
    }

    @Test
    public void createRestaurantShouldBuildRestaurantAndAddToRepo() {
        RestaurantRequest restaurantRequest = new RestaurantRequest("testName", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));

        Restaurant testRestaurant = restaurantService.createRestaurant(restaurantRequest);

        assertThat(testRestaurant.getName(), is("testName"));
        assertThat(testRestaurant.getId(), is(notNullValue()));
        // TODO fix this test (Wanted but not invoked)
        verify(repository).addRestaurant(testRestaurant);
    }

    @Test
    // TODO: Need to fix this test
    public void shouldUpdateRestaurantDeleteOldRestaurantAndUpdatedRestaurantToRepo() {
        Restaurant mockedRestaurant = mock(Restaurant.class);
        RestaurantRequest restaurantRequest = new RestaurantRequest("testName", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        Restaurant testRestaurant = restaurantService.createRestaurant(restaurantRequest);
        Restaurant updatedRestaurant = new Restaurant(testRestaurant.getId(), "testName2", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        restaurantService.updateRestaurant(testRestaurant, updatedRestaurant);
        verify(repository).deleteRestaurantById(testRestaurant.getId());
        verify(repository).addRestaurant(updatedRestaurant);
        // assertThat(repository.findRestaurantsByName(restaurantService.getAllRestaurants(), "testName2").contains("testName2"), is(true));
    }
}
