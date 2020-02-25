package com.portfolio.project.google.service;

import com.portfolio.project.google.domain.GoogleDirectionsDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class GoogleServiceTest {

    @Autowired
    private GoogleService googleService;

    @Test
    public void testFetchGoogleDirections() {
        GoogleDirectionsDto googleDirectionsDto = googleService.fetchGoogleDirections("Kraśnik", "Lublin");


    }
}