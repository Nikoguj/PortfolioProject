package com.portfolio.project.mapper;

import com.portfolio.project.domain.google.Point;
import com.portfolio.project.domain.google.PointDto;
import com.portfolio.project.domain.google.Points;
import com.portfolio.project.domain.google.PointsDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PointsMapper {

    public PointsDto mapToPointsDto(Points points) {
        return new PointsDto(points.getListOfAllPoints().stream()
            .map(point ->mapToPointDto(point))
            .collect(Collectors.toList()));
    }

    public PointDto mapToPointDto(Point point) {
        return new PointDto(point.getLat(), point.getLng(), point.getTimeFromLastPoint(), point.getArrivalTime());
    }
}
