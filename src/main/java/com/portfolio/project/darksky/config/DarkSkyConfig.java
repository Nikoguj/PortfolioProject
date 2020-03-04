package com.portfolio.project.darksky.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DarkSkyConfig {

    @Value("${DarkSky.api.key}")
    public String darkSkyAppKey;

    @Value("${DarSky.api.endpoint}")
    public String darkSkyApiEndpoint;
}