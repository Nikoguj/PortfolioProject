package com.portfolio.project.darksky.client;

import com.portfolio.project.darksky.config.DarkSkyConfig;
import com.portfolio.project.darksky.domain.DarkSkyForecastDto;
import com.portfolio.project.darksky.domain.DarkSkyPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class DarkSkyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DarkSkyClient.class);

    @Autowired
    private DarkSkyConfig darkSkyConfig;

    public Mono<DarkSkyForecastDto> getDarkSkyForecast(DarkSkyPoint darkSkyPoint) throws InterruptedException {
        Thread.sleep(15);

        try{
            return WebClient.create()
                    .get()
                    .uri(getURI(darkSkyPoint))
                    .retrieve()
                    .bodyToMono(DarkSkyForecastDto.class);
        }catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return WebClient.create().get().retrieve().bodyToMono(DarkSkyForecastDto.class);
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
