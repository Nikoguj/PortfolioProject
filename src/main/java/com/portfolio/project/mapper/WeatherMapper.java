package com.portfolio.project.mapper;

import com.portfolio.project.darksky.domain.DarkSkyCurrentlyAndHourlyDto;
import com.portfolio.project.domain.google.Weather;
import com.portfolio.project.domain.google.WeatherDto;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    public Weather mapToWeather(DarkSkyCurrentlyAndHourlyDto darkSkyCurrentlyAndHourlyDto) {
        return new Weather.WeatherBuilder()
                .setApparentTemperature(darkSkyCurrentlyAndHourlyDto.getApparentTemperature())
                .setCloudCover(darkSkyCurrentlyAndHourlyDto.getCloudCover())
                .setDewPoint(darkSkyCurrentlyAndHourlyDto.getDewPoint())
                .setHumidity(darkSkyCurrentlyAndHourlyDto.getHumidity())
                .setIcon(darkSkyCurrentlyAndHourlyDto.getIcon())
                .setPrecipAccumulation(darkSkyCurrentlyAndHourlyDto.getPrecipAccumulation())
                .setPrecipIntensity(darkSkyCurrentlyAndHourlyDto.getPrecipIntensity())
                .setPrecipProbability(darkSkyCurrentlyAndHourlyDto.getPrecipProbability())
                .setPrecipType(darkSkyCurrentlyAndHourlyDto.getPrecipType())
                .setPressure(darkSkyCurrentlyAndHourlyDto.getPressure())
                .setSummary(darkSkyCurrentlyAndHourlyDto.getSummary())
                .setTemperature(darkSkyCurrentlyAndHourlyDto.getTemperature())
                .setTime(darkSkyCurrentlyAndHourlyDto.getTime())
                .setVisibility(darkSkyCurrentlyAndHourlyDto.getVisibility())
                .setWindBearing(darkSkyCurrentlyAndHourlyDto.getWindBearing())
                .setWindGust(darkSkyCurrentlyAndHourlyDto.getWindGust())
                .setWindSpeed(darkSkyCurrentlyAndHourlyDto.getWindSpeed())
                .build();
    }

    public WeatherDto mapToWeatherDto(Weather weather) {
        return new WeatherDto(
                weather.getApparentTemperature(),
                weather.getCloudCover(),
                weather.getDewPoint(),
                weather.getHumidity(),
                weather.getIcon(),
                weather.getPrecipAccumulation(),
                weather.getPrecipIntensity(),
                weather.getPrecipProbability(),
                weather.getPrecipType(),
                weather.getPressure(),
                weather.getSummary(),
                weather.getTemperature(),
                weather.getTime(),
                weather.getVisibility(),
                weather.getWindSpeed(),
                weather.getWindGust(),
                weather.getWindBearing()
        );
    }
}