package service;

import com.ft.restaurants.domain.CreateRestaurantRequest;
import com.ft.restaurants.domain.Restaurant;
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
import static org.mockito.Mockito.verify;

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
        restaurantService = new RestaurantService();
    }

    @Test
    public void createRestaurantShouldBuildRestaurantAndAddToRepo() {
        CreateRestaurantRequest createRestaurantRequest = new CreateRestaurantRequest("testName", "testTag", "testAddress", "testCity", "testPostCode", 0, 0.00, 0.00);

        Restaurant testRestaurant = restaurantService.createRestaurant(createRestaurantRequest);

        assertThat(testRestaurant.getName(), is("testName"));
        assertThat(testRestaurant.getId(), is(notNullValue()));
        // TODO fix this test (Wanted but not invoked)
        verify(repository).addToRepository(testRestaurant);
    }
}