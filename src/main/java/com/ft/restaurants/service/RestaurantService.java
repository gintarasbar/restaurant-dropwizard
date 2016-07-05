package com.ft.restaurants.service;

import com.ft.restaurants.domain.Restaurant;
import com.ft.restaurants.domain.RestaurantRequest;
import com.ft.restaurants.domain.TagRequest;
import com.ft.restaurants.repository.RestaurantRepository;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Restaurant updateRestaurant = restaurant.copy()
                .id(restaurant.getId())
                .name(request.getName())
                .description(request.getDescription())
                .tag(request.getTag())
                .address(request.getAddress())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .hygieneRating(request.getHygieneRating())
                .location(request.getLocation())
                .build();
        repository.deleteRestaurantById(restaurant.getId());
        repository.addRestaurant(updateRestaurant);
        return request;
    }

    public Restaurant tagRestaurant(Restaurant restaurant, TagRequest tag) {
        String newTags = restaurant.getTag() + "/" + tag.getTag();

        if(tag.getTag().equals(""))
            newTags = restaurant.getTag();

        Restaurant taggedRestaurant = restaurant.copy()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .tag(newTags)
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .postcode(restaurant.getPostcode())
                .hygieneRating(restaurant.getHygieneRating())
                .location(restaurant.getLocation())
                .build();
        repository.deleteRestaurantById(restaurant.getId());
        repository.addRestaurant(taggedRestaurant);
        return taggedRestaurant;
    }

    public Optional<Restaurant> findRestaurantById(UUID id) {
        return repository.findRestaurantById(id);
    }

    public List<Restaurant> getAllRestaurants() {
        return repository.getRestaurants();
    }

    public List<Restaurant> filterByName(List<Restaurant> allRestaurants, String name) {
        if(!StringUtils.isBlank(name)) {
        return repository.findRestaurantsByName(allRestaurants, name);
        }
        return allRestaurants;
    }

    public List<Restaurant> filterByTag(List<Restaurant> allRestaurants, String tag) {
        if(!StringUtils.isBlank(tag)) {
            return repository.findRestaurantsByTag(allRestaurants, tag);
        }
        return allRestaurants;
    }

    public List<Restaurant> filterByDistance(List<Restaurant> allRestaurants, Double longitude, Double latitude, Double radius) {
        if(longitude != null || latitude != null || radius != null) {
            return repository.findRestaurantsByDistance(allRestaurants, longitude, latitude, radius);
        }
        return  allRestaurants;
    }

    public List<Restaurant> filterByAddress(List<Restaurant> allRestaurants, String address) {
        if(!StringUtils.isBlank(address)) {
            return repository.findRestaurantsByAddress(allRestaurants, address);
        }
        return allRestaurants;
    }

    public List<Restaurant> filterByPostCode(List<Restaurant> allRestaurants, String postCode) {
        if(!StringUtils.isBlank(postCode)) {
            return repository.findRestaurantsByPostCode(allRestaurants, postCode);
        }
        return allRestaurants;
    }

    public List<Restaurant> filterByHygieneRating(List<Restaurant> allRestaurants, Integer hygieneRating) {
        if(hygieneRating != null) {
            return repository.findRestaurantsByHygieneRating(allRestaurants, hygieneRating);
        }
        return allRestaurants;
    }

    public void saveCSV() {
        try {
            repository.saveData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

