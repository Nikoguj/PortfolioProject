package com.portfolio.project.domain.google;

import org.springframework.stereotype.Component;

@Component
public class MyMath {


    public double distance(Point startPoint, Point endPoint) {

        double lat1 = Math.toRadians(startPoint.getLat());
        double lng1 = Math.toRadians(startPoint.getLng());
        double lat2 = Math.toRadians(endPoint.getLat());
        double lng2 = Math.toRadians(endPoint.getLng());

        final int R = 6371;

        double dLng = lng2 - lng1;
        double dLat = lat2 - lat1;

        double ans = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLng / 2), 2);
        ans = 2 * Math.asin(Math.sqrt(ans));

        ans = ans * R;

        return ans * 1000;
    }
}