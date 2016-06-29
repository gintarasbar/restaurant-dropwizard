package com.ft.restaurants.domain;

import java.util.UUID;

/**
 * Created by Jorge on 6/21/2016.
 */
public final class RestaurantBuilder {
    private UUID id;
    private String name;
    private String description;
    private String tag;
    private String address;
    private String city;
    private String postcode;
    private Integer hygieneRating;
    private Location location;

    public RestaurantBuilder() {
    }

    public static RestaurantBuilder restaurantBuilder() {
        return new RestaurantBuilder();
    }

    public RestaurantBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public RestaurantBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RestaurantBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RestaurantBuilder tag(String tag) {
        this.tag = tag;
        return this;
    }

    public RestaurantBuilder address(String address) {
        this.address = address;
        return this;
    }

    public RestaurantBuilder city(String city) {
        this.city = city;
        return this;
    }

    public RestaurantBuilder postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public RestaurantBuilder hygieneRating(Integer hygieneRating) {
        this.hygieneRating = hygieneRating;
        return this;
    }

    public RestaurantBuilder location(Location location) {
        this.location = location;
        return this;
    }


    public Restaurant build() {
        Restaurant restaurant = new Restaurant(id, name, description, tag, address, city, postcode, hygieneRating, location);
        return restaurant;
    }
}
