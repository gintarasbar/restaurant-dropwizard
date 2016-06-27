package com.ft.restaurants.service;

import com.ft.restaurants.domain.Distance;
import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantRequest;
import com.ft.restaurants.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant createRestaurant(RestaurantRequest request) {
        Restaurant newRestaurant = Restaurant.copy(request).id(UUID.randomUUID()).build();
        repository.addRestaurant(newRestaurant);
        return newRestaurant;
    }

    public Restaurant updateRestaurant(Restaurant restaurant, Restaurant request) {
        repository.deleteRestaurantById(request.getId());
        repository.addRestaurant(request);
        return request;
    }

    public Optional<Restaurant> findRestaurantById(UUID id) {
        return null;
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.getRestaurants();
    }

    public List<Restaurant> filterByName(List<Restaurant> allRestaurants, String name) {
        return repository.findRestaurantsByName(allRestaurants, name);
    }

    public List<Restaurant> filterByDistance(List<Restaurant> filteredRestaurant, Double longitude, Double latitude, Double radius) {
        Location location = new Location(longitude, latitude);
        return filteredRestaurant
                .stream()
                .filter(restaurant -> Distance.distance(restaurant.getLocation(), location) <= radius)
                .collect(Collectors.toList());
    }
}

