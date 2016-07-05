package com.ft.restaurants.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static jersey.repackaged.com.google.common.base.Preconditions.checkNotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagRequest {
    @JsonProperty
    @NotEmpty
    private String tag;

    @JsonCreator
    public TagRequest(
            @JsonProperty("tag") String tag) {
        this.tag = checkNotNull(tag, "Tag cannot be null");
    }

    public String getTag() { return tag; }
}
