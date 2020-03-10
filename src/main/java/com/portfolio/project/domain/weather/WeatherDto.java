package com.portfolio.project.domain.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {

    private double apparentTemperature;
    private double cloudCover;
    private double dewPoint;
    private double humidity;
    private String icon;
    private double precipAccumulation;
    private double precipIntensity;
    private double precipProbability;
    private String precipType;
    private double pressure;
    private String summary;
    private double temperature;
    private int time;
    private double visibility;
    private double windSpeed;
    private double windGust;
    private int windBearing;
}
