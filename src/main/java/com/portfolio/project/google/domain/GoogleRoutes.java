package com.portfolio.project.google.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GoogleRoutes {

    private String copyrights;
    private List<GoogleLegs> legs;
}
