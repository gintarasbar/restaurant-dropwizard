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
    private double longitude;

    @JsonProperty
    private double latitude;


    // TODO: How to set individual fields then with big constructor..?
    public Restaurant(UUID id, String name, String tag, String address, String city, String postcode, Integer hygieneRating, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        if(hygieneRating == null)
            hygieneRating = 0;
        this.hygieneRating = hygieneRating;
        if(longitude == null)
            longitude = 0.00;
        this.longitude = longitude;
        if(latitude == null)
            latitude = 0.00;
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
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
                .longitude(longitude)
                .latitude(latitude);
    }


    // FOR POST
    public static RestaurantBuilder copy(CreateRestaurantRequest request) {
        return RestaurantBuilder.restaurantBuilder()
                .name(request.getName())
                .tag(request.getTag())
                .address(request.getAddress())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .hygieneRating(request.getHygieneRating())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude());
    }
}
