package com.portfolio.project.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleLegsDto {

    @JsonProperty("distance")
    private GoogleDistanceDto distance;

    @JsonProperty("duration")
    private GoogleDurationDto duration;

    @JsonProperty("end_address")
    private String endAddress;

    @JsonProperty("end_location")
    private GoogleLocationDto endLocation;

    @JsonProperty("start_address")
    private String startAddress;

    @JsonProperty("start_location")
    private GoogleLocationDto startLocation;

    @JsonProperty("steps")
    private List<GoogleStepsDto> steps;
}
