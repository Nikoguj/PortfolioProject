package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.domain.user.UsersMailDto;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {

    public UsersMail mapToUsersMail(final UsersMailDto usersMailDto) {
        return new UsersMail(
                usersMailDto.getId(),
                usersMailDto.getMail(),
                usersMailDto.isMailConfirmed());
    }

    public UsersMailDto mapToUsersMailDto(final UsersMail usersMail) {
        return new UsersMailDto(usersMail.getId(), usersMail.getMail(), usersMail.isMailConfirmed());
    }
}
