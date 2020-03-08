package com.portfolio.project.controller;


import com.portfolio.project.domain.google.PointsDto;
import com.portfolio.project.mapper.PointsMapper;
import com.portfolio.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/points")
public class PointsController {

    @Autowired
    private PointsService pointsService;

    @Autowired
    private PointsMapper pointsMapper;

    @GetMapping("getAllPoints/{directions}/{origin}")
    public PointsDto getAllPoints(@PathVariable String directions, @PathVariable String origin) {
        return pointsMapper.mapToPointsDto(pointsService.getAllPoints(directions, origin));
    }

    @GetMapping("getPoints/{directions}/{origin}/{distance}")
    public PointsDto getPoints(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance) throws InterruptedException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithDistanceAndTimeBetween(directions, origin, distance));
    }

    @GetMapping("getPointsWithArrivalTime/{directions}/{origin}/{distance}/{startTime}")
    public PointsDto getPoints(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance, @PathVariable int startTime) throws InterruptedException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithArrivalTime(directions, origin, distance, startTime));
    }

    @GetMapping("getPointsWithWeather/{directions}/{origin}/{distance}/{startTime}")
    public PointsDto getWeather(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance, @PathVariable int startTime) throws InterruptedException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithWeather(directions, origin, distance, startTime));
    }
}
