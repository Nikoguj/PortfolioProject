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
public class GoogleStepsDto {

    @JsonProperty("distance")
    private GoogleDistanceDto distance;

    @JsonProperty("duration")
    private GoogleDurationDto duration;

    @JsonProperty("end_location")
    private GoogleLocationDto endLocation;

    @JsonProperty("start_location")
    private GoogleLocationDto startLocation;

    @JsonProperty("polyline")
    private GooglePolylineDto googlePolyline;
}
