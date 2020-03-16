package com.portfolio.project.darksky.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyHourlyDto {

    @JsonProperty("data")
    private List<DarkSkyCurrentlyAndHourlyDto> darkSkyCurrentlyAndHourlyDtoList;
}
