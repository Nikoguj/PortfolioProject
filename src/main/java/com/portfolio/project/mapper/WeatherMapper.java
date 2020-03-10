package com.portfolio.project.mapper;

import com.portfolio.project.darksky.domain.DarkSkyCurrentlyAndHourlyDto;
import com.portfolio.project.domain.weather.Weather;
import com.portfolio.project.domain.weather.WeatherDto;
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
        double apparentTemperature;
        try{
            apparentTemperature = weather.getApparentTemperature();
        } catch (Exception e) {
            apparentTemperature = 0;
        }

        double cloudCover;
        try{
            cloudCover = weather.getCloudCover();
        } catch (Exception e) {
            cloudCover = 0;
        }

        double dewPoint;
        try{
            dewPoint = weather.getDewPoint();
        } catch (Exception e) {
            dewPoint = 0;
        }

        double humidity;
        try{
            humidity = weather.getHumidity();
        } catch (Exception e) {
            humidity = 0;
        }

        String icon;
        try{
            icon = weather.getIcon();
        } catch (Exception e) {
            icon = "";
        }

        double precipAccumulation;
        try{
            precipAccumulation = weather.getPrecipAccumulation();
        } catch (Exception e) {
            precipAccumulation = 0;
        }

        double precipIntensity;
        try{
            precipIntensity = weather.getPrecipIntensity();
        } catch (Exception e) {
            precipIntensity = 0;
        }

        double precipProbability;
        try{
            precipProbability = weather.getPrecipProbability();
        } catch (Exception e) {
            precipProbability = 0;
        }

        String precipType;
        try{
            precipType = weather.getPrecipType();
        } catch (Exception e) {
            precipType = "";
        }

        double pressure;
        try{
            pressure = weather.getPressure();
        } catch (Exception e) {
            pressure = 0;
        }

        String summary;
        try{
            summary = weather.getSummary();
        } catch (Exception e) {
            summary = "";
        }

        double temperature;
        try{
            temperature = weather.getTemperature();
        } catch (Exception e) {
            temperature = 0;
        }

        int time;
        try{
            time = weather.getTime();
        } catch (Exception e) {
            time = 0;
        }

        double visibility;
        try{
            visibility = weather.getVisibility();
        } catch (Exception e) {
            visibility = 0;
        }

        double windSpeed;
        try{
            windSpeed = weather.getWindSpeed();
        } catch (Exception e) {
            windSpeed = 0;
        }

        double windGust;
        try{
            windGust = weather.getWindGust();
        } catch (Exception e) {
            windGust = 0;
        }

        int windBearing;
        try{
            windBearing = weather.getWindBearing();
        } catch (Exception e) {
            windBearing = 0;
        }
        return new WeatherDto(
                apparentTemperature,
                cloudCover,
                dewPoint,
                humidity,
                icon,
                precipAccumulation,
                precipIntensity,
                precipProbability,
                precipType,
                pressure,
                summary,
                temperature,
                time,
                visibility,
                windSpeed,
                windGust,
                windBearing
        );
    }
}