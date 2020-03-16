package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UsersMailDto {
    private Long id;
    private String mail;
    private boolean mailConfirmed;
    private boolean scheduledMail;
}
