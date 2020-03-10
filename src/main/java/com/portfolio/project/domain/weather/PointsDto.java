package com.portfolio.project.domain.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointsDto {

    List<PointDto> listOfAllPoints = new ArrayList<>();
}
