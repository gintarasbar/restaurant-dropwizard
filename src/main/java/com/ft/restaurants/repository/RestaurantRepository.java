package com.ft.restaurants.repository;

import com.ft.restaurants.domain.Restaurant;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by Jorge on 6/21/2016.
 */
public class RestaurantRepository {
    public static Set<Restaurant> restaurants = new HashSet<>();

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addToRepository(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public Restaurant findRestaurantById(UUID id) {
        Restaurant restaurantFind = null;
        for(Restaurant restaurant : this.restaurants) {
            if(restaurant.getId().equals(id)) {
                restaurantFind = restaurant;
            }
        }
        return restaurantFind;
    }

    public Set<Restaurant> findRestaurantsByName(Set<Restaurant> restaurants, String regex) {
        Set<Restaurant> restaurantMatches = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);

        for (Restaurant restaurant : restaurants) {
            String restaurantName = restaurant.getName();
            if (pattern.matcher(restaurantName).matches()) {
                restaurantMatches.add(restaurant);
            }
        }
        return restaurantMatches;
    }
}

