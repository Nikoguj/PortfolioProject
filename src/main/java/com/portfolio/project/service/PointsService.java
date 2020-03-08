package com.portfolio.project.service;

import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import com.portfolio.project.darksky.service.DarkSkyService;
import com.portfolio.project.domain.google.*;
import com.portfolio.project.google.domain.*;
import com.portfolio.project.google.mapper.GoogleMapper;
import com.portfolio.project.google.service.GoogleService;
import com.portfolio.project.mapper.WeatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointsService {

    @Autowired
    private GoogleService googleService;

    @Autowired
    private GoogleMapper googleMapper;

    @Autowired
    private PolylineDecoder polylineDecoder;

    @Autowired
    private DarkSkyService darkSkyService;

    @Autowired
    private WeatherMapper weatherMapper;

    @Autowired
    private MyMath myMath;

    public Points getAllPoints(final String directions, final String origin) {
        GoogleDirections googleDirections = googleMapper.mapToDirections(googleService.fetchGoogleDirections(origin, directions));

        List<GoogleSteps> googleStepsList = googleDirections.getRoutes().get(0).getLegs().get(0).getSteps();

        Points points = new Points();

        for (GoogleSteps googleSteps : googleStepsList) {
            points.getListOfAllPoints().add(new Point(googleSteps.getStartLocation().getLat(), googleSteps.getStartLocation().getLng()));
            points.getListOfAllPoints().addAll(polylineDecoder.decode(googleSteps.getGooglePolyline().getPoints()));
            points.getListOfAllPoints().add(new Point(googleSteps.getEndLocation().getLat(), googleSteps.getEndLocation().getLng()));
        }

        return points;
    }

    public Points getPointsWithDistance(final String directions, final String origin, final int distance) {

        Points points = getAllPoints(directions, origin);

        List<Point> pointList = points.getListOfAllPoints();

        List<Point> newPointList = new ArrayList<>();
        newPointList.add(points.getListOfAllPoints().get(0));

        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i; j < pointList.size(); j++) {
                if (myMath.distance(pointList.get(i), pointList.get(j)) > distance) {
                    newPointList.add(pointList.get(j));
                    i = j;
                    break;
                }
            }
        }

        newPointList.add(points.getListOfAllPoints().get(points.getListOfAllPoints().size() - 1));

        return new Points(newPointList);
    }


    public Points getPointsWithDistanceAndTimeBetween(final String directions, final String origin, final int distance) throws InterruptedException {

        Points points = getPointsWithDistance(directions, origin, distance);

        for (int i = 0; i < points.getListOfAllPoints().size() - 1; i++) {
            String directionsString1 = points.getListOfAllPoints().get(i).getLat() + "," + points.getListOfAllPoints().get(i).getLng();
            String directionsString2 = points.getListOfAllPoints().get(i + 1).getLat() + "," + points.getListOfAllPoints().get(i + 1).getLng();

            Mono<GoogleDirectionsDto> mono = googleService.fetchGoogleDirectionsWebClient(directionsString1, directionsString2);

            int finalI = i+1;
            mono.subscribe(t -> points.getListOfAllPoints().get(finalI).setTimeFromLastPoint(t.getRoutes().get(0).getLegs().get(0).getDuration().getValue()));
        }
        Thread.sleep(1000);

        return points;
    }

    public Points getPointsWithArrivalTime(final String directions, final String origin, final int distance, final int startTime) throws InterruptedException {
        Points points = getPointsWithDistanceAndTimeBetween(directions, origin, distance);

        points.getListOfAllPoints().get(0).setArrivalTime(startTime);

        for (int i = 1; i < points.getListOfAllPoints().size(); i++) {
            points.getListOfAllPoints().get(i).setArrivalTime(points.getListOfAllPoints().get(i - 1).getArrivalTime() + points.getListOfAllPoints().get(i).getTimeFromLastPoint());
        }

        return points;
    }

    public Points getPointsWithWeather(final String directions, final String origin, final int distance, final int startTime) throws InterruptedException {
        Points points = getPointsWithArrivalTime(directions, origin, distance, startTime);

        for (int i = 0; i < points.getListOfAllPoints().size(); i++) {
            DarkSkyPoint darkSkyPoint = new DarkSkyPoint(points.getListOfAllPoints().get(i).getLat(), points.getListOfAllPoints().get(i).getLng());

            Mono<DarkSkyForecastDto> mono = darkSkyService.fetchDarkSkyForecast(darkSkyPoint);

            int finalI = i;
            mono.subscribe(t -> {
                int time = points.getListOfAllPoints().get(finalI).getArrivalTime();

                for (int j = 0; j < t.getDarkSkyHourly().getDarkSkyCurrentlyAndHourlyDtoList().size(); j++) {
                    Weather weather = weatherMapper.mapToWeather(t.getDarkSkyHourly().getDarkSkyCurrentlyAndHourlyDtoList().get(j));
                    Weather weather2;
                    try {
                        weather2 = weatherMapper.mapToWeather(t.getDarkSkyHourly().getDarkSkyCurrentlyAndHourlyDtoList().get(j+1));
                    } catch (Exception e) {
                        weather2 = null;
                    }
                    if (weather2 != null && weather.getTime() < time && weather2.getTime() > time) {
                        points.getListOfAllPoints().get(finalI).setWeather(weather);
                    }
                }
            });
        }


        Thread.sleep(1000);
        return points;
    }
}