package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginStatusDto {
    private String status;
    private String sessionKey;
    private Long userID;
}
