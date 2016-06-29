package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jorge on 6/18/2016.
 */
public class Restaurant {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    private String tag;

    @JsonProperty
    private String address;

    @JsonProperty
    private String city;

    @JsonProperty
    private String postcode;

    @JsonProperty
    private Integer hygieneRating;

    @JsonProperty
    private Location location;

    @JsonCreator
    public Restaurant(@JsonProperty("id") UUID id,
                      @JsonProperty("name") String name,
                      @JsonProperty("description") String description,
                      @JsonProperty("tag") String tag,
                      @JsonProperty("address") String address,
                      @JsonProperty("city") String city,
                      @JsonProperty("postcode") String postcode,
                      @JsonProperty("hygieneRating") Integer hygieneRating,
                      @JsonProperty("location") Location location) {
        this.id = checkNotNull(id, "Restaurant id cannot be null");
        this.name = checkNotNull(name, "Restaurant name cannot be null");
        this.description = description;
        this.tag = tag;
        this.address = address;
        this.city = city;
        this.postcode = postcode;
        this.hygieneRating = hygieneRating;
        this.location = location;
    }

    public UUID getId() { return id; }

    public String getName() {
        return name;
    }

    public String getDescription() { return description; }

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

    public Location getLocation() {
        return location;
    }

    // FOR PUT
    public RestaurantBuilder copy() {
        return RestaurantBuilder.restaurantBuilder()
                .id(id)
                .name(name)
                .description(description)
                .tag(tag)
                .address(address)
                .city(city)
                .postcode(postcode)
                .hygieneRating(hygieneRating)
                .location(location);
    }


    // FOR POST
    public static RestaurantBuilder copy(RestaurantRequest request) {
        return RestaurantBuilder.restaurantBuilder()
                .name(request.getName())
                .description(request.getDescription())
                .tag(request.getTag())
                .address(request.getAddress())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .hygieneRating(request.getHygieneRating())
                .location(request.getLocation());
    }
}
