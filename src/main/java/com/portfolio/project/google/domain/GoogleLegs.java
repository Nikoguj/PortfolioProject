package com.portfolio.project.google.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GoogleLegs {

    private GoogleDistance distance;
    private GoogleDuration duration;
    private String endAddress;
    private GoogleLocation endLocation;
    private String startAddress;
    private GoogleLocation startLocation;
    private List<GoogleSteps> steps;

}
