package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersDto;
import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.domain.user.UsersMailDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class UsersMapperTest {

    @Test
    public void mapToUsersDto() {
        //Given
        UsersMapper usersMapper = new UsersMapper();
        Users users = new Users("login1", "password1");
        UsersMail usersMail = new UsersMail(1L,"mail1", false);
        usersMail.setPinConfirmMail("pin");
        users.setUsersMail(usersMail);

        //When
        UsersDto usersDto = usersMapper.mapToUsersDto(users);

        //Then
        Assert.assertEquals("login1", usersDto.getLogin());
        Assert.assertEquals("password1", usersDto.getPassword());
        Assert.assertEquals(false, usersDto.isPhoneNumberConfirmed());
        Assert.assertNotNull(users.getCreateDate());
    }

    @Test
    public void mapToUsers() {
        //Given
        UsersMapper usersMapper = new UsersMapper();
        UsersDto usersDto = new UsersDto(1L, "login1", "password1", "name1", "surname1", "567567567", false, new Date(), new Date(), new UsersMailDto());

        //When
        Users users = usersMapper.mapToUsers(usersDto);

        //Then
        Assert.assertNotEquals(java.util.Optional.of(0), users.getId());
        Assert.assertEquals("login1", users.getLogin());
        Assert.assertEquals("password1", users.getPassword());
        Assert.assertEquals("surname1", users.getSurname());
        Assert.assertEquals("567567567", users.getPhoneNumber());
        Assert.assertEquals(false, users.isPhoneNumberConfirmed());
    }
}