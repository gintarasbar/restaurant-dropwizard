package com.ft.restaurants.domain;


import static java.lang.StrictMath.*;

public class Distance {
    private final static Double earthRadius = 6371.00;

    public static double distance(Location location1, Location location2) {
        Double distanceLongitude = location2.getLongitude() - location1.getLongitude();
        Double distanceLatitude = location2.getLatitude() - location1.getLatitude();


        Double a = ((sin(distanceLatitude / 2)) * (sin(distanceLatitude / 2)))
                    + (cos(location1.getLatitude()) * cos(location2.getLatitude()))
                    * (sin(distanceLongitude / 2) * sin(distanceLongitude / 2));
        Double c = 2 * atan2(sqrt(a), sqrt(1-a));
        return earthRadius * c;
    }


}
