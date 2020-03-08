package com.portfolio.project.darksky.service;

import com.portfolio.project.darksky.client.DarkSkyClient;
import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DarkSkyService {

    @Autowired
    private DarkSkyClient darkSkyClient;

    public Mono<DarkSkyForecastDto> fetchDarkSkyForecast(final DarkSkyPoint darkSkyPoint) throws InterruptedException {
        return darkSkyClient.getDarkSkyForecast(darkSkyPoint);
    }
}