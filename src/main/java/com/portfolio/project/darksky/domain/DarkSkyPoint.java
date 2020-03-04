package com.portfolio.project.darksky.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DarkSkyPoint {

    private Double lat;
    private Double lng;

    @Override
    public String toString() {
        return String.valueOf(lat) + "," + String.valueOf(lng);
    }
}
