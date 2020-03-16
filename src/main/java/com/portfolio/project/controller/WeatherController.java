package com.portfolio.project.controller;


import com.portfolio.project.domain.weather.PointsDto;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.mapper.PointsMapper;
import com.portfolio.project.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private PointsService pointsService;

    @Autowired
    private PointsMapper pointsMapper;

    @GetMapping("getPoints/{directions}/{origin}")
    public PointsDto getAllPoints(@PathVariable String directions, @PathVariable String origin, @RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        return pointsMapper.mapToPointsDto(pointsService.getAllPoints(directions, origin, userSessionKey, userId));
    }

    @GetMapping("getPoints/{directions}/{origin}/{distance}")
    public PointsDto getPointsDistance(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance, @RequestParam String userSessionKey, @RequestParam Long userId) throws InterruptedException, UserNotFoundException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithDistanceAndTimeBetween(directions, origin, distance, userSessionKey, userId));
    }

    @GetMapping("getPoints/{directions}/{origin}/{distance}/{startTime}")
    public PointsDto getPointsWithArrivalTime(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance, @PathVariable int startTime, @RequestParam String userSessionKey, @RequestParam Long userId) throws InterruptedException, UserNotFoundException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithArrivalTime(directions, origin, distance, startTime, userSessionKey, userId));
    }

    @GetMapping("getPointsWeather/{directions}/{origin}/{distance}/{startTime}")
    public PointsDto getWeather(@PathVariable String directions, @PathVariable String origin, @PathVariable int distance, @PathVariable int startTime, @RequestParam String userSessionKey, @RequestParam Long userId) throws InterruptedException, UserNotFoundException {
        return pointsMapper.mapToPointsDto(pointsService.getPointsWithWeather(directions, origin, distance, startTime, userSessionKey, userId));
    }
}
