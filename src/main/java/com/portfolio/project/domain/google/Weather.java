package com.portfolio.project.domain.google;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather {

    private double apparentTemperature;
    private double cloudCover;
    private double dewPoint;
    private double humidity;
    private String icon;
    private double precipAccumulation;
    private double precipIntensity;
    private double precipProbability;
    private String precipType;
    private double pressure;
    private String summary;
    private double temperature;
    private int time;
    private double visibility;
    private double windSpeed;
    private double windGust;
    private int windBearing;

    public static class WeatherBuilder {
        private double apparentTemperature;
        private double cloudCover;
        private double dewPoint;
        private double humidity;
        private String icon;
        private double precipAccumulation;
        private double precipIntensity;
        private double precipProbability;
        private String precipType;
        private double pressure;
        private String summary;
        private double temperature;
        private int time;
        private double visibility;
        private double windSpeed;
        private double windGust;
        private int windBearing;

        public WeatherBuilder setApparentTemperature(double apparentTemperature) {
            this.apparentTemperature = apparentTemperature;
            return this;
        }

        public WeatherBuilder setCloudCover(double cloudCover) {
            this.cloudCover = cloudCover;
            return this;
        }

        public WeatherBuilder setDewPoint(double dewPoint) {
            this.dewPoint = dewPoint;
            return this;
        }

        public WeatherBuilder setHumidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public WeatherBuilder setPrecipAccumulation(double precipAccumulation) {
            this.precipAccumulation = precipAccumulation;
            return this;
        }

        public WeatherBuilder setPrecipIntensity(double precipIntensity) {
            this.precipIntensity = precipIntensity;
            return this;
        }

        public WeatherBuilder setPrecipProbability(double precipProbability) {
            this.precipProbability = precipProbability;
            return this;
        }

        public WeatherBuilder setPrecipType(String precipType) {
            this.precipType = precipType;
            return this;
        }

        public WeatherBuilder setPressure(double pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder setSummary(String summary) {
            this.summary = summary;
            return this;
        }

        public WeatherBuilder setTemperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherBuilder setTime(int time) {
            this.time = time;
            return this;
        }

        public WeatherBuilder setVisibility(double visibility) {
            this.visibility = visibility;
            return this;
        }

        public WeatherBuilder setWindSpeed(double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WeatherBuilder setWindGust(double windGust) {
            this.windGust = windGust;
            return this;
        }

        public WeatherBuilder setWindBearing(int windBearing) {
            this.windBearing = windBearing;
            return this;
        }

        public Weather build() {
            return new Weather(apparentTemperature, cloudCover, dewPoint, humidity, icon, precipAccumulation, precipIntensity, precipProbability, precipType, pressure, summary, temperature, time, visibility, windSpeed, windGust, windBearing);
        }
    }

    public Weather(double apparentTemperature, double cloudCover, double dewPoint, double humidity, String icon, double precipAccumulation, double precipIntensity, double precipProbability, String precipType, double pressure, String summary, double temperature, int time, double visibility, double windSpeed, double windGust, int windBearing) {
        this.apparentTemperature = apparentTemperature;
        this.cloudCover = cloudCover;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.icon = icon;
        this.precipAccumulation = precipAccumulation;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.pressure = pressure;
        this.summary = summary;
        this.temperature = temperature;
        this.time = time;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windBearing = windBearing;
    }
}
