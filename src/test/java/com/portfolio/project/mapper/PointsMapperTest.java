package com.portfolio.project.mapper;

import com.portfolio.project.domain.weather.Point;
import com.portfolio.project.domain.weather.PointDto;
import com.portfolio.project.domain.weather.Points;
import com.portfolio.project.domain.weather.PointsDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointsMapperTest {

    @Autowired
    private PointsMapper pointsMapper;

    @Test
    public void testMapToPointsDto() {
        //Given
        Points points = new Points();
        Point point1 = new Point(24.564, 45.2346);
        Point point2 = new Point(21.1256, 45.2346);
        Point point3 = new Point(34.12353, 24.5623);
        points.getListOfAllPoints().add(point1);
        points.getListOfAllPoints().add(point2);
        points.getListOfAllPoints().add(point3);

        //When
        PointsDto pointsDto = pointsMapper.mapToPointsDto(points);

        //Then
        Assert.assertEquals(point1.getArrivalTime(), pointsDto.getListOfAllPoints().get(0).getArrivalTime());
    }

    @Test
    public void mapToPointDto() {
        //Given
        Point point = new Point(123.4523, 42.4234);

        //When
        PointDto pointDto = pointsMapper.mapToPointDto(point);

        //Then
        Assert.assertEquals(point.getLat(), pointDto.getLat(), 0.04);
        Assert.assertEquals(point.getLng(), pointDto.getLng(), 0.04);
        Assert.assertEquals(point.getArrivalTime(), pointDto.getArrivalTime(), 0.04);
    }
}