package com.ft.restaurants.service;

import com.ft.restaurants.domain.CreateRestaurantRequest;
import com.ft.restaurants.domain.Restaurant;

import java.util.UUID;


/**
 * Created by Jorge on 6/19/2016.
 */
public class RestaurantService {

    /*public Restaurant findRestaurantById(UUID id) {
        // TODO: Implement findById
        *//*Restaurant restaurant;
        return restaurant;*//*
    }*/

    // TODO: Implement this method
    public Restaurant createRestaurant(CreateRestaurantRequest request) {
        Restaurant newRestaurant = Restaurant.copy(request).id(UUID.randomUUID()).build();
        // TODO: Add to RestaurantRepository
        return newRestaurant;
    }

    public Restaurant updateRestaurant(Restaurant restaurant) {
       // restaurant.copy().address(qsdsqds).build()
        restaurant.copy().build();
        return null;
    }

    /*public List<Restaurant> getRestaurants() {
        return restaurants;
    }*/
}

