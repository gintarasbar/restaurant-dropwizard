package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

/**
 * Created by Jorge on 6/21/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantRequest {

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

    @JsonCreator
    public RestaurantRequest(
            @JsonProperty("name") String name,
            @JsonProperty("tag") String tag,
            @JsonProperty("address") String address,
            @JsonProperty("city") String city,
            @JsonProperty("postcode") String postcode,
            @JsonProperty("hygieneRating") int hygieneRating,
            @JsonProperty("longitude") double longitude,
            @JsonProperty("latitude") double latitude) {
        this.name = checkNotNull(name, "Restaurant name cannot be null");
        this.tag = defaultIfNull(tag, "");
        this.address = defaultIfNull(address, "");
        this.city = defaultIfNull(city, "");
        this.postcode = defaultIfNull(postcode, "");
        this.hygieneRating = defaultIfNull(hygieneRating, null);
        this.longitude = defaultIfNull(longitude, null);
        this.latitude = defaultIfNull(latitude, null);
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
