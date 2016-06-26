package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Jorge on 6/26/2016.
 */
public class Location {
    @JsonProperty
    private Double longitude;

    @JsonProperty
    private Double latitude;

    public Location(Double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
