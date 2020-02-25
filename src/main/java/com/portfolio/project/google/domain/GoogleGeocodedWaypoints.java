package com.portfolio.project.google.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GoogleGeocodedWaypoints {

    private String geocoderStatus;
    private String placeId;
}