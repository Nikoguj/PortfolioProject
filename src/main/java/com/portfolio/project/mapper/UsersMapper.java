package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsersMapper {

    public List<UsersDto> mapToUsersDtoList(final List<Users> usersList) {
        return usersList.stream()
                .map(this::mapToUsersDto)
                .collect(Collectors.toList());
    }

    public UsersDto mapToUsersDto(final Users users) {
        MailMapper mailMapper = new MailMapper();
        return new UsersDto(
                users.getId(),
                users.getLogin(),
                users.getPassword(),
                users.getName(),
                users.getSurname(),
                users.getPhoneNumber(),
                users.isPhoneNumberConfirmed(),
                users.getCreateDate(),
                users.getLastLogin(),
                mailMapper.mapToUsersMailDto(users.getUsersMail()),
                users.isAdmin(),
                users.isBlock());
    }

    public Users mapToUsers(final UsersDto usersDto) {
        return new Users(
                usersDto.getId(),
                usersDto.getLogin(),
                usersDto.getPassword(),
                usersDto.getName(),
                usersDto.getSurname(),
                usersDto.getPhoneNumber(),
                usersDto.isPhoneNumberConfirmed(),
                usersDto.getCreateDate(),
                usersDto.getLastLogin(),
                usersDto.isAdmin(),
                usersDto.isBlock());
                //mailMapper.mapToUsersMail(usersDto.getUsersMailDto()));
    }

}