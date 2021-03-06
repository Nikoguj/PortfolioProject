package com.portfolio.project.domain.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PointDto {

    private final double lat;
    private final double lng;
    private final int timeFromLastPoint;
    private final int arrivalTime;
    private final WeatherDto weatherDto;

}
