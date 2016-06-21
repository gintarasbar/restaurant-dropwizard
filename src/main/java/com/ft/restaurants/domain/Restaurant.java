package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * Created by Jorge on 6/18/2016.
 */
public class Restaurant {
    @JsonProperty
    @NotEmpty
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
}
