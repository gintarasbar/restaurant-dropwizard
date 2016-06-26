package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import static java.lang.StrictMath.*;

/**
 * Created by Jorge on 6/26/2016.
 */
public class Distance {
    private final Double earthRadius = 6371.00;

    @JsonProperty
    private Double distance;

    public Distance(Location location1, Location location2) {
        Double distanceLongitude = location2.getLongitude() - location1.getLongitude();
        Double distanceLatitude = location2.getLatitude() - location1.getLatitude();


        Double a = ((sin(distanceLatitude / 2)) * (sin(distanceLatitude / 2)))
                    + (cos(location1.getLatitude()) * cos(location2.getLatitude()))
                    * (sin(distanceLongitude / 2) * sin(distanceLongitude / 2));
        Double c = 2 * atan2(sqrt(a), sqrt(1-a));
        this.distance = this.earthRadius * c;
    }
}
