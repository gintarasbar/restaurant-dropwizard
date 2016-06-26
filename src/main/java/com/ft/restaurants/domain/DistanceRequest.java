package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jorge on 6/26/2016.
 */
public class DistanceRequest {
    @JsonProperty
    @NotEmpty
    private Double longitude;

    @JsonProperty
    @NotEmpty
    private Double latitude;

    @JsonCreator
    public DistanceRequest(
            @JsonProperty("longitude") Double longitude,
            @JsonProperty("latitude") Double latitude) {
        this.longitude = checkNotNull(longitude, "Longitude cannot be null");
        this.latitude = checkNotNull(latitude, "Latitude cannot be null");
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
