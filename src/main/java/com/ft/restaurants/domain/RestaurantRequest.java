package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

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
    private Location location;

    @JsonCreator
    public RestaurantRequest(
            @JsonProperty("name") String name,
            @JsonProperty("tag") String tag,
            @JsonProperty("address") String address,
            @JsonProperty("city") String city,
            @JsonProperty("postcode") String postcode,
            @JsonProperty("hygieneRating") int hygieneRating,
            @JsonProperty("location") Location location) {
        this.name = checkNotNull(name, "Restaurant name cannot be null");
        this.tag = defaultIfNull(tag, "");
        this.address = defaultIfNull(address, "");
        this.city = defaultIfNull(city, "");
        this.postcode = defaultIfNull(postcode, "");
        this.hygieneRating = defaultIfNull(hygieneRating, null);
        this.location = defaultIfNull(location, new Location(null, null));
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

    public Location getLocation() {
        return location;
    }

}
