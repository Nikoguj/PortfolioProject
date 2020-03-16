package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.domain.user.UsersMailDto;
import org.springframework.stereotype.Component;

@Component
public class MailMapper {

    public UsersMailDto mapToUsersMailDto(final UsersMail usersMail) {
        return new UsersMailDto(usersMail.getId(), usersMail.getMail(), usersMail.isMailConfirmed(), usersMail.isScheduledMail());
    }
}
