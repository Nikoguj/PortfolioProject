package com.portfolio.project.darksky.service;

import com.portfolio.project.darksky.client.DarkSkyClient;
import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DarkSkyService {

    @Autowired
    private DarkSkyClient darkSkyClient;

    public DarkSkyForecastDto fetchDarkSkyForecast(final DarkSkyPoint darkSkyPoint) {
        return darkSkyClient.getDarkSkyForecast(darkSkyPoint);
    }
}