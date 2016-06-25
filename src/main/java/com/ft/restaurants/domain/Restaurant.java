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

    public Restaurant(UUID id, String name, String tag, String address, String city, String postcode, Integer hygieneRating, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.hygieneRating = hygieneRating;
        this.longitude = longitude;
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
    public RestaurantBuilder copyUpdate(CreateRestaurantRequest request) {
        String updatedName = request.getName();
        String updatedTag = request.getTag();
        String updatedAddress = request.getAddress();
        String updatedCity = request.getCity();
        String updatedPostCode = request.getPostcode();
        Integer updatedHygieneRating = request.getHygieneRating();
        Double updatedLongitude = request.getLongitude();
        Double updatedLatitude = request.getLatitude();

        if(updatedName == "")
             updatedName = name;
        if (updatedTag == "")
            updatedTag = tag;
        if (updatedAddress == "")
            updatedAddress = address;
        if (updatedCity == "")
            updatedCity = city;
        if (updatedPostCode == "")
            updatedPostCode = postcode;
        if (updatedHygieneRating == 0)
            updatedHygieneRating = hygieneRating;
        if (updatedLongitude == 0.00)
            updatedLongitude = longitude;
        if (updatedLatitude == 0.00)
            updatedLatitude = latitude;

        return RestaurantBuilder.restaurantBuilder()
                .id(id)
                .name(updatedName)
                .tag(updatedTag)
                .address(updatedAddress)
                .city(updatedCity)
                .postcode(updatedPostCode)
                .hygieneRating(updatedHygieneRating)
                .longitude(updatedLongitude)
                .latitude(updatedLatitude);
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
