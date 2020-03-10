package com.portfolio.project.mapper;

import com.portfolio.project.domain.weather.Point;
import com.portfolio.project.domain.weather.PointDto;
import com.portfolio.project.domain.weather.Points;
import com.portfolio.project.domain.weather.PointsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PointsMapper {

    @Autowired
    private WeatherMapper weatherMapper;

    public PointsDto mapToPointsDto(Points points) {
        return new PointsDto(points.getListOfAllPoints().stream()
            .map(this::mapToPointDto)
            .collect(Collectors.toList()));
    }

    public PointDto mapToPointDto(Point point) {
        return new PointDto(point.getLat(), point.getLng(), point.getTimeFromLastPoint(), point.getArrivalTime(), weatherMapper.mapToWeatherDto(point.getWeather()));
    }
}
