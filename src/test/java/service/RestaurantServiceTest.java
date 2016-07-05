package service;

import com.ft.restaurants.domain.*;
import com.ft.restaurants.repository.RestaurantRepository;
import com.ft.restaurants.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        RestaurantRequest restaurantRequest = new RestaurantRequest("testName", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        Restaurant testRestaurant = restaurantService.createRestaurant(restaurantRequest);
        Restaurant updatedRestaurant = new Restaurant(testRestaurant.getId(), "testName2", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        restaurantService.updateRestaurant(testRestaurant, updatedRestaurant);
        verify(repository).deleteRestaurantById(testRestaurant.getId());
        verify(repository, times(2)).addRestaurant(any(Restaurant.class));
        // assertThat(repository.findRestaurantsByName(restaurantService.getAllRestaurants(), "testName2").contains("testName2"), is(true));
    }

    @Test
    public void shouldTagRestaurant() {
        TagRequest tagRequest = new TagRequest("testTag2");
        RestaurantRequest restaurantRequest = new RestaurantRequest("testName", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        Restaurant testRestaurant = restaurantService.createRestaurant(restaurantRequest);
        Restaurant taggedRestaurant = restaurantService.tagRestaurant(testRestaurant, tagRequest);

        assertThat(taggedRestaurant.getTag(), is("testTag/testTag2"));
    }

    @Test
    public void shouldNotTagRestaurantIfTagRequestIsEmpty() {
        TagRequest tagRequest = new TagRequest("");
        RestaurantRequest restaurantRequest = new RestaurantRequest("testName", "testDescription", "testType", "testTag",
                "testAddress", "testCity", "testPostCode",
                0, new Location(0.00, 0.00));
        Restaurant testRestaurant = restaurantService.createRestaurant(restaurantRequest);
        Restaurant taggedRestaurant = restaurantService.tagRestaurant(testRestaurant, tagRequest);

        assertThat(taggedRestaurant.getTag(), is("testTag"));
    }

    @Test
    public void shouldFindRestaurantByIdAndCallRepository() {
        restaurantService.findRestaurantById(any(UUID.class));
        verify(repository).findRestaurantById(any(UUID.class));
    }

    @Test
    public void shouldGetAllRestaurantsAndCallRepository() {
        restaurantService.getAllRestaurants();
        verify(repository).getRestaurants();
    }

    @Test
    public void shouldCallRepositoryFindRestaurantsByNameWhenFilterByNameIsCalled() {
        List<Restaurant> testList = new ArrayList<>();
        String testName = "testName";
        restaurantService.filterByName(testList, testName);
        verify(repository).findRestaurantsByName(testList, testName);
    }

    @Test
    public void shouldCallRepositoryFindRestaurantByTagWhenFilterByTagIsCalled() {
        List<Restaurant> testList = new ArrayList<>();
        String testTag = "testTag";
        restaurantService.filterByTag(testList, testTag);
        verify(repository).findRestaurantsByTag(testList, testTag);
    }

    @Test
    public void shouldReturnAllRestaurantsListWhenFilterByParameterTagParameterIsBlank() {
        List<Restaurant> testList = new ArrayList<>();
        String testTag = null;
        assertThat(restaurantService.filterByTag(testList, testTag), is(testList));
    }

    @Test
    public void shouldCallRepositoryFindRestaurantsByAddressWhenFilterByAddressIsCalled() {
        List<Restaurant> testList = new ArrayList<>();
        String testAddress = "testAddress";
        restaurantService.filterByAddress(testList, testAddress);
        verify(repository).findRestaurantsByAddress(testList, testAddress);
    }

    @Test
    public void shouldReturnAllRestaurantsListWhenFilterByAddressParameterIsBlank() {
        List<Restaurant> testList = new ArrayList<>();
        String testAddress = null;
        assertThat(restaurantService.filterByTag(testList, testAddress), is(testList));
    }

    @Test
    public void shouldReturnAllRestaurantsListWhenFilterByNameParameterIsBlank() {
        List<Restaurant> testList = new ArrayList<>();
        String testName = null;
        assertThat(restaurantService.filterByName(testList, testName), is(testList));
    }

    @Test
    public void shouldCallRepositoryFindRestaurantByDistanceWhenFilterByDistanceIsCalled() {
        List<Restaurant> testList = new ArrayList<>();
        Double testLongitude = 1.0;
        Double testLatitude = 1.0;
        Double testRadius = 1.0;
        restaurantService.filterByDistance(testList, testLongitude, testLatitude, testRadius);
        verify(repository).findRestaurantsByDistance(testList, testLongitude, testLatitude, testRadius);
    }

    @Test
    public void shouldReturnAllRestaurantsListIfLongitudeIsNull() {
        List<Restaurant> testList = new ArrayList<>();
        Double testLongitude = null;
        Double testLatitude = 1.0;
        Double testRadius = 1.0;
        assertThat(restaurantService.filterByDistance(testList, testLongitude, testLatitude, testRadius), is(testList));
    }

    @Test
    public void shouldReturnAllRestaurantsListIfLatitudeIsNull() {
        List<Restaurant> testList = new ArrayList<>();
        Double testLongitude = 1.0;
        Double testLatitude = null;
        Double testRadius = 1.0;
        assertThat(restaurantService.filterByDistance(testList, testLongitude, testLatitude, testRadius), is(testList));
    }

    @Test
    public void shouldReturnAllRestaurantsListIfRadiusIsNull() {
        List<Restaurant> testList = new ArrayList<>();
        Double testLongitude = 1.0;
        Double testLatitude = 1.0;
        Double testRadius = null;
        assertThat(restaurantService.filterByDistance(testList, testLongitude, testLatitude, testRadius), is(testList));
    }

    @Test
    public void shouldCallRepositorySaveDataWhenSaveCsvIsCalled() throws Exception {
        restaurantService.saveCSV();
        verify(repository).saveData();
    }
}
