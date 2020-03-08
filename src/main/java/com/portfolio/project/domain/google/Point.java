package com.portfolio.project.domain.google;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {

    private double lat;
    private double lng;
    private int timeFromLastPoint;
    private int arrivalTime;
    private Weather weather;

    public Point(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    @Override
    public String toString() {
        return "Point{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", timeFromLastPoint=" + timeFromLastPoint +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (Double.compare(point.lat, lat) != 0) return false;
        return Double.compare(point.lng, lng) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}