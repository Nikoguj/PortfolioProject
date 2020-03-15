package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersMailDto {
    private Long id;
    private String mail;
    private boolean mailConfirmed;
}
