package com.portfolio.project.darksky.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyCurrentlyAndHourlyDto {

    @JsonProperty("apparentTemperature")
    private double apparentTemperature;

    @JsonProperty("cloudCover")
    private double cloudCover;

    @JsonProperty("dewPoint")
    private double dewPoint;

    @JsonProperty("humidity")
    private double humidity;

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("precipAccumulation")
    private double precipAccumulation;

    @JsonProperty("precipIntensity")
    private double precipIntensity;

    @JsonProperty("precipProbability")
    private double precipProbability;

    @JsonProperty("precipType")
    private String precipType;

    @JsonProperty("pressure")
    private double pressure;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("temperature")
    private double temperature;

    @JsonProperty("time")
    private int time;

    @JsonProperty("visibility")
    private double visibility;

    @JsonProperty("windSpeed")
    private double windSpeed;

    @JsonProperty("windGust")
    private double windGust;

    @JsonProperty("windBearing")
    private int windBearing;
}
