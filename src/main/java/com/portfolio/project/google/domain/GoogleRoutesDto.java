package com.portfolio.project.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleRoutesDto {

    @JsonProperty("copyrights")
    private String copyrights;

    @JsonProperty("legs")
    private List<GoogleLegsDto> legs;
}
