package com.portfolio.project.domain.weather;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherTest {

    @Test
    public void testConstructor() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setApparentTemperature(10);

        //Then
        Assert.assertEquals(10, weather.getApparentTemperature(), 0.03);
    }

    @Test
    public void setGetApparentTemperature() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setApparentTemperature(10);

        //Then
        Assert.assertEquals(10, weather.getApparentTemperature(), 0.03);
    }

    @Test
    public void setGetCloudCover() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setCloudCover(10);

        //Then
        Assert.assertEquals(10, weather.getCloudCover(), 0.03);
    }

    @Test
    public void setGetDewPoint() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setDewPoint(10);

        //Then
        Assert.assertEquals(10, weather.getDewPoint(), 0.03);
    }

    @Test
    public void setGetHumidity() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setHumidity(10);

        //Then
        Assert.assertEquals(10, weather.getHumidity(), 0.03);
    }

    @Test
    public void setGetIcon() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setIcon("icon");

        //Then
        Assert.assertEquals("icon", weather.getIcon());
    }

    @Test
    public void setGetPrecipAccumulation() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setPrecipAccumulation(10);

        //Then
        Assert.assertEquals(10, weather.getPrecipAccumulation(), 0.03);
    }

    @Test
    public void setGetPrecipIntensity() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setPrecipIntensity(10);

        //Then
        Assert.assertEquals(10, weather.getPrecipIntensity(), 0.03);
    }

    @Test
    public void setGetPrecipProbability() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setPrecipProbability(10);

        //Then
        Assert.assertEquals(10, weather.getPrecipProbability(), 0.03);
    }

    @Test
    public void setGetPrecipType() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setPrecipType("type");

        //Then
        Assert.assertEquals("type", weather.getPrecipType());
    }

    @Test
    public void setGetPressure() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setPressure(1000);

        //Then
        Assert.assertEquals(1000, weather.getPressure(), 0.03);
    }

    @Test
    public void setGetSummary() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setSummary("summary");

        //Then
        Assert.assertEquals("summary", weather.getSummary());
    }

    @Test
    public void setGetTemperature() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setTemperature(10);

        //Then
        Assert.assertEquals(10, weather.getTemperature(), 0.03);
    }

    @Test
    public void setGetTime() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setTime(10);

        //Then
        Assert.assertEquals(10, weather.getTime());
    }

    @Test
    public void setGetVisibility() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setVisibility(10);

        //Then
        Assert.assertEquals(10, weather.getVisibility(), 0.03);
    }

    @Test
    public void setGetWindSpeed() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setWindSpeed(10);

        //Then
        Assert.assertEquals(10, weather.getWindSpeed(), 0.03);
    }

    @Test
    public void setGetWindGust() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setWindGust(10);

        //Then
        Assert.assertEquals(10, weather.getWindGust(), 0.03);
    }

    @Test
    public void setGetWindBearing() {
        //Given
        Weather weather = new Weather();

        //When
        weather.setWindBearing(10);

        //Then
        Assert.assertEquals(10, weather.getWindBearing(), 0.03);
    }
}