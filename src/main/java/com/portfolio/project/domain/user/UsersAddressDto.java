package com.portfolio.project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsersAddressDto {
    private Long id;
    private String Origin;
    private String Destination;
}
