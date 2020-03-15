package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UsersDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean phoneNumberConfirmed;
    private Date createDate;
    private Date lastLogin;
    private UsersMailDto usersMailDto;
}
