package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * Created by Jorge on 6/18/2016.
 */
public class Restaurant {

    @JsonProperty
    private UUID id;

    @JsonProperty
    @NotEmpty
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
    public Restaurant(UUID id, String name, String tag, String address, String city, String postcode, int hygieneRating, double longitude, double latitude) {
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


    // TODO: fix 'Non-static field address cannot be referenced from a static context
    public static RestaurantBuilder copy(CreateRestaurantRequest request) {
        return RestaurantBuilder.restaurantBuilder()
                .address(address);
    }
}
