package com.portfolio.project.google.controller;

import com.portfolio.project.google.domain.GoogleDirectionsDto;
import com.portfolio.project.google.service.GoogleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/google")
public class GoogleController {

    @Autowired
    private GoogleService googleService;

    @GetMapping("directions/{origin}/{destination}")
    public GoogleDirectionsDto getDirections(@PathVariable String origin, @PathVariable String destination) {
        return googleService.fetchGoogleDirections(origin, destination);
    }
}
