package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by Jorge on 6/18/2016.
 */
public class Restaurant {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String tag;

    @JsonProperty
    private String address;

    @JsonProperty
    private String city;

    @JsonProperty
    private String postcode;

    @JsonProperty
    private int hygieneRating;

    @JsonProperty
    private Location location;

    public Restaurant(UUID id, String name, String tag, String address, String city, String postcode, Integer hygieneRating, Location location) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.hygieneRating = hygieneRating;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public int getHygieneRating() {
        return hygieneRating;
    }

    public Location getLocation() {
        return location;
    }

    // FOR PUT
    public RestaurantBuilder copy() {
        return RestaurantBuilder.restaurantBuilder()
                .id(id)
                .name(name)
                .tag(tag)
                .address(address)
                .city(city)
                .postcode(postcode)
                .hygieneRating(hygieneRating)
                .location(location);
    }


    // FOR POST
    public static RestaurantBuilder copy(RestaurantRequest request) {
        return RestaurantBuilder.restaurantBuilder()
                .name(request.getName())
                .tag(request.getTag())
                .address(request.getAddress())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .hygieneRating(request.getHygieneRating())
                .location(request.getLocation());
    }
}
