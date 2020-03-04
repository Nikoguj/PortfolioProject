package com.portfolio.project.service;

import com.portfolio.project.domain.google.MyMath;
import com.portfolio.project.domain.google.Point;
import com.portfolio.project.domain.google.Points;
import com.portfolio.project.domain.google.PolylineDecoder;
import com.portfolio.project.google.domain.GoogleDirections;
import com.portfolio.project.google.domain.GoogleSteps;
import com.portfolio.project.google.mapper.GoogleMapper;
import com.portfolio.project.google.service.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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

        List<Point> pointList = leaveEveryTwentieth(points).getListOfAllPoints();

        List<Point> newPointList = new ArrayList<>();
        newPointList.add(points.getListOfAllPoints().get(0));

        for (int i = 0; i < pointList.size(); i++) {
            for (int j = i; j < pointList.size(); j++) {
                if(myMath.distance(pointList.get(i), pointList.get(j)) > distance) {
                    newPointList.add(pointList.get(j));
                    i = j;
                    break;
                }
            }
        }

        newPointList.add(points.getListOfAllPoints().get(points.getListOfAllPoints().size() - 1));

        return new Points(newPointList);
    }

    private Points leaveEveryTwentieth(Points points) {

        Points newPoints = new Points();

        newPoints.getListOfAllPoints().add(points.getListOfAllPoints().get(0));

        for (int i = 0; i < points.getListOfAllPoints().size()-1; i++) {
            if(i % 200 == 0) {
                newPoints.getListOfAllPoints().add(points.getListOfAllPoints().get(i));
            }
        }

        newPoints.getListOfAllPoints().add(points.getListOfAllPoints().get(points.getListOfAllPoints().size()-1));

        return newPoints;
    }

    public Points getPointsWithDistanceAndTimeBetween(final String directions, final String origin, final int distance) {

        Points points = getPointsWithDistance(directions, origin, distance);

        for (int i = 0; i < points.getListOfAllPoints().size()-1; i++) {
            String directionsString1 = String.valueOf(points.getListOfAllPoints().get(i).getLat()) + "," + String.valueOf(points.getListOfAllPoints().get(i).getLng());
            String directionsString2 = String.valueOf(points.getListOfAllPoints().get(i+1).getLat()) + "," + String.valueOf(points.getListOfAllPoints().get(i+1).getLng());
            Long startTime = System.currentTimeMillis();
            GoogleDirections googleDirections = googleMapper.mapToDirections(googleService.fetchGoogleDirections(directionsString1, directionsString2));
            points.getListOfAllPoints().get(i+1).setTimeFromLastPoint(googleDirections.getRoutes().get(0).getLegs().get(0).getDuration().getValue());
            Long endTime = System.currentTimeMillis();
            System.out.println("Time: " + String.valueOf(endTime - startTime));

        }


        return points;
    }

    public Points getPointsWithArrivalTime(final String directions, final String origin, final int distance, final int startTime) {
        Points points = getPointsWithDistanceAndTimeBetween(directions, origin, distance);

        points.getListOfAllPoints().get(0).setArrivalTime(startTime);

        for (int i = 1; i < points.getListOfAllPoints().size(); i++) {
            points.getListOfAllPoints().get(i).setArrivalTime(points.getListOfAllPoints().get(i-1).getArrivalTime() + points.getListOfAllPoints().get(i).getTimeFromLastPoint());
        }

        return points;
    }
}