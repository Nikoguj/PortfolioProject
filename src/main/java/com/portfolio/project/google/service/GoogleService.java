package com.portfolio.project.google.service;

import com.portfolio.project.google.client.GoogleClient;
import com.portfolio.project.google.domain.GoogleDirectionsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleService {

    @Autowired
    private GoogleClient googleClient;

    public GoogleDirectionsDto fetchGoogleDirections(final String origin, final String destination) {
        return googleClient.getGoogleDirections(origin, destination);
    }
}