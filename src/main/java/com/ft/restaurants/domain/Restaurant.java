package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Jorge on 6/18/2016.
 */
public class Restaurant {
    @JsonProperty
    @NotEmpty
    private String id;

    @JsonProperty
    @NotEmpty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String address;

    @JsonProperty
    private int coordinates;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(int coordinates) {
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
