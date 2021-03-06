package com.portfolio.project.darksky.controller;

import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import com.portfolio.project.darksky.service.DarkSkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/darksky/")
public class DarkSkyController {

    @Autowired
    private DarkSkyService darkSkyService;

    @GetMapping("forecast/{lat}/{lng}")
    public Mono<DarkSkyForecastDto> getForecast(@PathVariable double lat, @PathVariable double lng) throws InterruptedException {
        DarkSkyPoint darkSkyPoint = new DarkSkyPoint(lat, lng);
        return darkSkyService.fetchDarkSkyForecast(darkSkyPoint);
    }
}
