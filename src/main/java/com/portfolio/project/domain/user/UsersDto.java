package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
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
    private boolean admin;
    private boolean block;
}
