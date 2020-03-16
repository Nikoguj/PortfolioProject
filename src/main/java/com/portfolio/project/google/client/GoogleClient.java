package com.portfolio.project.google.client;

import com.portfolio.project.google.config.GoogleConfig;
import com.portfolio.project.google.domain.GoogleDirectionsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GoogleClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleClient.class);

    @Autowired
    private GoogleConfig googleConfig;

    @Autowired
    private RestTemplate restTemplate;


    public GoogleDirectionsDto getGoogleDirections(String origin, String destination) {

        URI url = getURI(origin, destination);

        try{
            return restTemplate.getForObject(url, GoogleDirectionsDto.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new GoogleDirectionsDto();
        }
    }

    private URI getURI(String origin, String destination) {
        return UriComponentsBuilder.fromHttpUrl(googleConfig.getGoogleApiEndpoint() + "directions/json")
                .queryParam("key", googleConfig.getGoogleAppKey())
                .queryParam("origin", origin)
                .queryParam("destination", destination).build().encode().toUri();
    }
}
