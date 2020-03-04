package com.portfolio.project.domain.google;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PointsDto {

    List<PointDto> listOfAllPoints = new ArrayList<>();
}
