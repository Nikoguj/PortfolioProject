package com.portfolio.project.mapper;

import com.portfolio.project.darksky.domain.DarkSkyCurrentlyAndHourlyDto;
import com.portfolio.project.domain.weather.Weather;
import com.portfolio.project.domain.weather.WeatherDto;
import org.junit.Assert;
import org.junit.Test;

public class WeatherMapperTest {

    @Test
    public void mapToWeather() {
        //Given
        WeatherMapper weatherMapper = new WeatherMapper();

        DarkSkyCurrentlyAndHourlyDto darkSkyCurrentlyAndHourlyDto = new DarkSkyCurrentlyAndHourlyDto(
                10,
                0.3,
                3,
                0.44,
                "icon",
                0.54,
                0.80,
                0.09,
                "type",
                1000,
                "summary",
                10,
                123543,
                16.00,
                10,
                15,
                30
        );

        //When
        Weather weather = weatherMapper.mapToWeather(darkSkyCurrentlyAndHourlyDto);

        //Then
        Assert.assertEquals(10, weather.getApparentTemperature(), 0.004);
        Assert.assertEquals(0.3, weather.getCloudCover(), 0.004);
        Assert.assertEquals(3, weather.getDewPoint(), 0.004);
        Assert.assertEquals(0.44, weather.getHumidity(), 0.004);
        Assert.assertEquals("icon", weather.getIcon());
        Assert.assertEquals(0.54, weather.getPrecipAccumulation(), 0.004);
        Assert.assertEquals(0.80, weather.getPrecipIntensity(), 0.004);
        Assert.assertEquals(0.09, weather.getPrecipProbability(), 0.004);
        Assert.assertEquals("type", weather.getPrecipType());
        Assert.assertEquals(1000, weather.getPressure(), 0.004);
        Assert.assertEquals("summary", weather.getSummary());
        Assert.assertEquals(10, weather.getTemperature(), 0.004);
        Assert.assertEquals(123543, weather.getTime(), 0.004);
        Assert.assertEquals(16.00, weather.getVisibility(), 0.004);
        Assert.assertEquals(10, weather.getWindSpeed(), 0.004);
        Assert.assertEquals(15, weather.getWindGust(), 0.004);
        Assert.assertEquals(30, weather.getWindBearing(), 0.004);
    }

    @Test
    public void mapToWeatherDto() {
        //Given
        WeatherMapper weatherMapper = new WeatherMapper();

        Weather weather = new Weather(
                10,
                0.3,
                3,
                0.44,
                "icon",
                0.54,
                0.80,
                0.09,
                "type",
                1000,
                "summary",
                10,
                123543,
                16.00,
                10,
                15,
                30
        );

        WeatherDto weatherDto = weatherMapper.mapToWeatherDto(weather);

        Assert.assertEquals(10, weatherDto.getApparentTemperature(), 0.004);
        Assert.assertEquals(0.3, weatherDto.getCloudCover(), 0.004);
        Assert.assertEquals(3, weatherDto.getDewPoint(), 0.004);
        Assert.assertEquals(0.44, weatherDto.getHumidity(), 0.004);
        Assert.assertEquals("icon", weatherDto.getIcon());
        Assert.assertEquals(0.54, weatherDto.getPrecipAccumulation(), 0.004);
        Assert.assertEquals(0.80, weatherDto.getPrecipIntensity(), 0.004);
        Assert.assertEquals(0.09, weatherDto.getPrecipProbability(), 0.004);
        Assert.assertEquals("type", weatherDto.getPrecipType());
        Assert.assertEquals(1000, weatherDto.getPressure(), 0.004);
        Assert.assertEquals("summary", weatherDto.getSummary());
        Assert.assertEquals(10, weatherDto.getTemperature(), 0.004);
        Assert.assertEquals(123543, weatherDto.getTime(), 0.004);
        Assert.assertEquals(16.00, weatherDto.getVisibility(), 0.004);
        Assert.assertEquals(10, weatherDto.getWindSpeed(), 0.004);
        Assert.assertEquals(15, weatherDto.getWindGust(), 0.004);
        Assert.assertEquals(30, weatherDto.getWindBearing(), 0.004);
    }
}