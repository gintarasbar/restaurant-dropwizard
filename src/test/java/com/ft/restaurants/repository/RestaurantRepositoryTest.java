package com.ft.restaurants.repository;

import com.ft.restaurants.CSVReader;
import com.ft.restaurants.CSVWriter;
import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantBuilder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantRepositoryTest {

    @Mock
    CSVReader csvReader;

    @Mock
    CSVWriter csvWriter;

    @InjectMocks
    RestaurantRepository sut;

    private Restaurant restaurant;

    @Before
    public void onSetup() {
        restaurant = new RestaurantBuilder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000000"))
                .name("testName")
                .tag("testTag")
                .address("testAddress")
                .city("testCity")
                .postcode("testPostCode")
                .hygieneRating(0)
                .location(new Location(0.0, 0.0))
                .build();
        sut.addRestaurant(restaurant);
    }

    @Test
    public void shouldLoadTheDataFromCSV() throws Exception {
        Restaurant restaurant = this.restaurant.copy().id(UUID.randomUUID()).build();
        given(csvReader.readCSV(anyString())).willReturn(newArrayList(restaurant));
        sut.loadData();
        assertThat(sut.getRestaurants(),contains(restaurant));
    }

    @Test
    public void shouldGetAllRestaurants() throws Exception {
        assertThat(sut.getRestaurants(), contains(restaurant));
    }


    @Test
    public void shouldFindRestaurantById() throws Exception {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Restaurant restaurant2 = restaurant.copy()
                .id(id)
                .build();
        sut.addRestaurant(restaurant2);
        assertThat(sut.findRestaurantById(id).isPresent(), is(true));
        assertThat(sut.findRestaurantById(id).get(), is(restaurant2));
    }

    @Test
    public void shouldNotFindRestaurantById() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        assertThat(sut.findRestaurantById(id).isPresent(), is(false));
    }

    @Test
    public void shouldDeleteRestaurantById() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        Restaurant restaurant2 = restaurant.copy()
                .id(id)
                .build();

        sut.addRestaurant(restaurant2);
        assertThat(sut.findRestaurantById(id).isPresent(), is(true));
        sut.deleteRestaurantById(id);
        assertThat(sut.findRestaurantById(id).isPresent(), is(false));
    }

    // TODO: Implement for the other methods
    @Test
    public void findRestaurantsByName() throws Exception {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String name = "testName2";
        Restaurant restaurant2 = restaurant.copy()
                    .id(id)
                    .name(name)
                    .build();
        sut.addRestaurant(restaurant2);
        // assertThat(sut.findRestaurantsByName(sut.getRestaurants(), name).get(1), is(restaurant2));
        assertThat(sut.findRestaurantsByName(sut.getRestaurants(), name), IsIterableContainingInOrder.contains(restaurant2));
    }

    @Test
    public void findRestaurantsByAddress() throws Exception {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String address = "testAddress2";
        Restaurant restaurant2 = restaurant.copy()
                    .id(id)
                    .address(address)
                    .build();
        sut.addRestaurant(restaurant2);
        //assertThat(sut.findRestaurantsByAddress(sut.getRestaurants(), address).get(1), is(restaurant2));
        assertThat(sut.findRestaurantsByAddress(sut.getRestaurants(), address), IsIterableContainingInOrder.contains(restaurant2));
    }

}