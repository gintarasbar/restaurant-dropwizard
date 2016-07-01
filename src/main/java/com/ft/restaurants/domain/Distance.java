package com.ft.restaurants.domain;


import static java.lang.Math.*;

public class Distance {
    private final static Double earthRadius = 6371.0;

    public static double distance(Location location1, Location location2) {
        double distanceLongitude = Math.toRadians(location2.getLongitude() - location1.getLongitude());
        double distanceLatitude = Math.toRadians(location2.getLatitude() - location1.getLatitude());


        double a = ((sin(distanceLatitude / 2.0)) * (sin(distanceLatitude / 2.0)))
                    + (cos(location1.getLatitude()) * cos(location2.getLatitude()))
                    * (sin(distanceLongitude / 2.0) * sin(distanceLongitude / 2.0));
        double c = 2.0 * atan2(sqrt(a), sqrt(1.0 - a));
        return earthRadius * c;
    }

}
