package com.portfolio.project.factory;

import com.portfolio.project.domain.user.Users;
import org.junit.Assert;
import org.junit.Test;

public class UserFactoryTest {

    @Test
    public void makeUsers1() {
        //Given
        UserFactory userFactory = new UserFactory();

        //When
        Users user = userFactory.makeUsers(UserFactory.USERS1);

        //Then
        Assert.assertEquals("login1", user.getLogin());
        Assert.assertEquals("password1", user.getPassword());
        Assert.assertEquals(true, user.isAdmin());
        Assert.assertEquals("name1", user.getName());
        Assert.assertEquals("surname1", user.getSurname());
        Assert.assertEquals("123456789", user.getPhoneNumber());
        Assert.assertEquals("origin1", user.getUsersAddressList().get(0).getOrigin());
        Assert.assertEquals("destination1", user.getUsersAddressList().get(0).getDestination());
        Assert.assertEquals("mail1", user.getUsersMail().getMail());
        Assert.assertEquals(false, user.getUsersMail().isScheduledMail());
        Assert.assertEquals(false, user.getUsersMail().isMailConfirmed());
    }

    @Test
    public void makeUsers2() {
        //Given
        UserFactory userFactory = new UserFactory();

        //When
        Users user = userFactory.makeUsers(UserFactory.USERS2);

        //Then
        Assert.assertEquals("login2", user.getLogin());
        Assert.assertEquals("password2", user.getPassword());
        Assert.assertEquals(false, user.isAdmin());
        Assert.assertEquals("name2", user.getName());
        Assert.assertEquals("surname2", user.getSurname());
        Assert.assertEquals("345762344", user.getPhoneNumber());
        Assert.assertEquals("origin2", user.getUsersAddressList().get(0).getOrigin());
        Assert.assertEquals("destination2", user.getUsersAddressList().get(0).getDestination());
        Assert.assertEquals("mail2", user.getUsersMail().getMail());
        Assert.assertEquals(false, user.getUsersMail().isScheduledMail());
        Assert.assertEquals(false, user.getUsersMail().isMailConfirmed());
    }

    @Test
    public void makeUsers3() {
        //Given
        UserFactory userFactory = new UserFactory();

        //When
        Users user = userFactory.makeUsers(UserFactory.USERS3);

        //Then
        Assert.assertEquals("login3", user.getLogin());
        Assert.assertEquals("password3", user.getPassword());
        Assert.assertEquals(false, user.isAdmin());
        Assert.assertEquals("name3", user.getName());
        Assert.assertEquals("surname3", user.getSurname());
        Assert.assertEquals("456546234", user.getPhoneNumber());
        Assert.assertEquals("origin3", user.getUsersAddressList().get(0).getOrigin());
        Assert.assertEquals("destination3", user.getUsersAddressList().get(0).getDestination());
        Assert.assertEquals("mail3", user.getUsersMail().getMail());
        Assert.assertEquals(true, user.getUsersMail().isScheduledMail());
        Assert.assertEquals(false, user.getUsersMail().isMailConfirmed());
    }
}