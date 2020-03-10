package com.portfolio.project.domain.weather;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PointsTest {

    @Test
    public void testPoints() {
        //Given
        Point point1 = new Point(1.423, 51.421);
        Point point2 = new Point(45.2155, 25.1734);
        Point point3 = new Point(33.5342, 47.37234);

        Points points = new Points();
        points.listOfAllPoints.add(point1);
        points.listOfAllPoints.add(point2);
        points.listOfAllPoints.add(point3);

        //When
        String exceptedString = "Points{listOfAllPoints=[Point{lat=1.423, lng=51.421, timeFromLastPoint=0}, Point{lat=45.2155, lng=25.1734, timeFromLastPoint=0}, Point{lat=33.5342, lng=47.37234, timeFromLastPoint=0}]}";

        List<Point> pointList = new ArrayList<>();
        pointList.add(point1);
        pointList.add(point2);
        pointList.add(point3);
        //Then
        Assert.assertEquals(exceptedString, points.toString());
        Assert.assertEquals(pointList, points.getListOfAllPoints());
    }

    @Test
    public void testConstructors() {
        //When
        Points points1 = new Points();

        Point point1 = new Point(1.423, 51.421);
        Point point2 = new Point(45.2155, 25.1734);
        Point point3 = new Point(33.5342, 47.37234);
        ArrayList<Point> pointArrayList = new ArrayList<>();
        pointArrayList.add(point1);
        pointArrayList.add(point2);
        pointArrayList.add(point3);
        Points points2 = new Points(pointArrayList);

        //When & Then
        Assert.assertEquals(0, points1.getListOfAllPoints().size());
        Assert.assertEquals(3, points2.getListOfAllPoints().size());
    }
}