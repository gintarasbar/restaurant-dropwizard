package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty
    private Double longitude;

    @JsonProperty
    private Double latitude;

    @JsonCreator
    public Location(@JsonProperty("longitude")Double longitude,
                    @JsonProperty("latitude")  Double latitude) {
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
