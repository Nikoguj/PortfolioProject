package com.portfolio.project.google.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GoogleDirections {

    private List<GoogleGeocodedWaypoints> geocodedWaypoints;
    private List<GoogleRoutes> routes;
    private String status;
}
