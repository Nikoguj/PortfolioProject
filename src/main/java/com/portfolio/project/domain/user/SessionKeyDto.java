package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SessionKeyDto {
    private Long id;
    private String sessionKey;
    private LocalDateTime termOfValidity;
}
