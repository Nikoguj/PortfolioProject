package com.portfolio.project.google.client;

import com.portfolio.project.google.config.GoogleConfig;
import com.portfolio.project.google.domain.GoogleDirectionsDto;
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
public class GoogleClientWebClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleClient.class);

    @Autowired
    private GoogleConfig googleConfig;

    public Mono<GoogleDirectionsDto> getGoogleDirections(String origin, String destination) throws InterruptedException {
        Thread.sleep(15);
        try {
            return WebClient.create()
                    .get()
                    .uri(getURI(origin, destination))
                    .retrieve()
                    .bodyToMono(GoogleDirectionsDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return WebClient.create().get().retrieve().bodyToMono(GoogleDirectionsDto.class);
        }
    }

    private URI getURI(String origin, String destination) {
        return UriComponentsBuilder.fromHttpUrl(googleConfig.getGoogleApiEndpoint() + "directions/json")
                .queryParam("key", googleConfig.getGoogleAppKey())
                .queryParam("origin", origin)
                .queryParam("destination", destination).build().encode().toUri();
    }
}