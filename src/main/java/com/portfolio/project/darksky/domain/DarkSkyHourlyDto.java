package com.portfolio.project.darksky.domain;

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
public class DarkSkyHourlyDto {

    @JsonProperty("data")
    private List<DarkSkyCurrentlyAndHourlyDto> darkSkyCurrentlyAndHourlyDtoList;
}
