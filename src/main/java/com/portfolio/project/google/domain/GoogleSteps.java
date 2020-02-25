package com.portfolio.project.google.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GoogleSteps {

    private GoogleDistance distance;
    private GoogleDuration duration;
    private GoogleLocation endLocation;
    private GoogleLocation startLocation;
    private GooglePolyline googlePolyline;
}
