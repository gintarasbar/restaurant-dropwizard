package com.ft.restaurants.domain;

public enum RestaurantType {
    BURGER("Burger"), ASIAN("Asian"), OTHER("Other"), NULL("");

    private String type;

    RestaurantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
