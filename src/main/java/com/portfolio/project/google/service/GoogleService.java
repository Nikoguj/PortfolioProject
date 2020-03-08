package com.portfolio.project.google.service;

import com.portfolio.project.google.client.GoogleClient;
import com.portfolio.project.google.client.GoogleClientWebClient;
import com.portfolio.project.google.domain.GoogleDirectionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GoogleService {

    @Autowired
    private GoogleClient googleClient;

    @Autowired
    private GoogleClientWebClient googleClientWebClient;

    public GoogleDirectionsDto fetchGoogleDirections(final String origin, final String destination) {
        return googleClient.getGoogleDirections(origin, destination);
    }

    public Mono<GoogleDirectionsDto> fetchGoogleDirectionsWebClient(final String origin, final String destination) throws InterruptedException {
        return googleClientWebClient.getGoogleDirections(origin, destination);
    }
}