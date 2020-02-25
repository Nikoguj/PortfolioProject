package com.portfolio.project.google.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GoogleLocation {

    private Double lat;
    private Double lng;
}
