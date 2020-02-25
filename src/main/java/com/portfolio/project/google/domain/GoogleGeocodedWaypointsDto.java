package com.portfolio.project.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleGeocodedWaypointsDto {

    @JsonProperty("geocoder_status")
    private String geocoderStatus;

    @JsonProperty("place_id")
    private String placeId;
}
