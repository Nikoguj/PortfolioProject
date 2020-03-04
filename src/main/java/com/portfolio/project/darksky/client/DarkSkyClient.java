package com.portfolio.project.darksky.client;

import com.portfolio.project.darksky.config.DarkSkyConfig;
import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class DarkSkyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DarkSkyClient.class);

    @Autowired
    private DarkSkyConfig darkSkyConfig;

    @Autowired
    private RestTemplate restTemplate;

    public DarkSkyForecastDto getDarkSkyForecast(DarkSkyPoint darkSkyPoint) {

        URI url = getURI(darkSkyPoint);
        System.out.println(url);
        try{
            DarkSkyForecastDto darkSkyForecastDto = restTemplate.getForObject(url, DarkSkyForecastDto.class);
            return darkSkyForecastDto;
        }catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new DarkSkyForecastDto();
        }
    }

    public URI getURI(DarkSkyPoint darkSkyPoint) {
        return UriComponentsBuilder.fromHttpUrl(
                darkSkyConfig.getDarkSkyApiEndpoint() + "forecast/")
                .path(darkSkyConfig.getDarkSkyAppKey() + "/")
                .path(darkSkyPoint.toString())
                .queryParam("units", "si").build().encode().toUri();
    }
}
