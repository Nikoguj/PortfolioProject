package com.portfolio.project.domain.weather;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    @Test
    public void testToString() {
        //Given
        Point point = new Point(1.0, 2.0);

        //When
        String exceptedString = "Point{lat=1.0, lng=2.0, timeFromLastPoint=0}";

        //Then
        Assert.assertEquals(exceptedString, point.toString());
    }

    @Test
    public void testEquals() {
        //Given
        Point point1 = new Point(4.4, 5.1);
        Point point2 = new Point(4.4, 5.1);
        Point point3 = new Point(10.45, 312.32);

        //When & Then
        Assert.assertEquals(true, point1.equals(point2));
        Assert.assertNotEquals(true, point2.equals(point3));
    }

    @Test
    public void testHashCode() {
        //Given
        Point point = new Point(103.2, 23.55);

        //When
        int exceptedHashCode = -1828306912;

        //Then
        Assert.assertEquals(exceptedHashCode, point.hashCode());
    }

    @Test
    public void setGetLatLng() {
        //Given
        Point point = new Point(123.4523, 543.2341);

        //When
        double lat = 40.164;
        double lng = 42.52;

        point.setLat(lat);
        point.setLng(lng);

        //Then
        Assert.assertEquals(lat, point.getLat(), 0.04);
        Assert.assertEquals(lng, point.getLng(), 0.04);
    }

    @Test
    public void setGetTimeFromLastPoint() {
        //Given
        Point point = new Point(23.531, 64654345);

        //When
        point.setTimeFromLastPoint(2345);

        //Then
        Assert.assertEquals(2345, point.getTimeFromLastPoint(), 0.05);
    }

    @Test
    public void setGetArrivalTime() {
        //Given
        Point point = new Point(23.531, 64654345);

        //When
        point.setArrivalTime(123762345);

        //Then
        Assert.assertEquals(123762345, point.getArrivalTime(), 0.05);
    }

    @Test
    public void setGetWeather() {
        //Given
        Point point = new Point(23.531, 64654345);

        Weather weather = new Weather.WeatherBuilder()
                .setApparentTemperature(4.24)
                .setCloudCover(0.55)
                .setDewPoint(3.0)
                .setHumidity(0.03)
                .setIcon("icon")
                .setPrecipAccumulation(0.03)
                .setPrecipIntensity(0.4)
                .setPrecipProbability(0.22)
                .setPrecipType("type")
                .setPressure(1000)
                .setSummary("summary")
                .setTemperature(10)
                .setTime(1583749951)
                .setVisibility(16)
                .setWindBearing(170)
                .setWindGust(3.56)
                .setWindSpeed(2.30)
                .build();

        //When
        point.setWeather(weather);

        //Then
        Assert.assertEquals(weather, point.getWeather());
    }
}