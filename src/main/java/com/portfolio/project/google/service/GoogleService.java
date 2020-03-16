package com.portfolio.project.google.service;

import com.portfolio.project.google.client.GoogleClient;
import com.portfolio.project.google.client.GoogleClientWebClient;
import com.portfolio.project.google.domain.GoogleDirectionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class GoogleService {

    @Autowired
    private GoogleClient googleClient;

    @Autowired
    private GoogleClientWebClient googleClientWebClient;

    public GoogleDirectionsDto fetchGoogleDirections(final String origin, final String destination) {
        GoogleDirectionsDto googleDirectionsDto = googleClient.getGoogleDirections(origin, destination);
        if(googleDirectionsDto.getStatus().equals("OK")) {
            return googleDirectionsDto;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location not found");
        }
    }

    public Mono<GoogleDirectionsDto> fetchGoogleDirectionsWebClient(final String origin, final String destination) throws InterruptedException {
        return googleClientWebClient.getGoogleDirections(origin, destination);
    }
}