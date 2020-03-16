package com.portfolio.project.factory;

import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersAddress;
import com.portfolio.project.domain.user.UsersMail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserFactory {
    public static final String USERS1 = "USERS1";
    public static final String USERS2 = "USERS2";
    public static final String USERS3 = "USERS3";

    public final Users makeUsers(final String USERS) {
        switch (USERS) {
            case USERS1:
                Users user1 = new Users("login1", "password1");
                user1.setName("name1");
                user1.setSurname("surname1");
                user1.setAdmin(true);
                user1.setPhoneNumber("123456789");
                List<UsersAddress> usersAddressList = new ArrayList<>();
                usersAddressList.add(new UsersAddress("origin1", "destination1"));
                user1.setUsersAddressList(usersAddressList);
                UsersMail usersMail = new UsersMail();
                usersMail.setMail("mail1");
                usersMail.setScheduledMail(false);
                usersMail.generatePinConfirm();
                usersMail.setMailConfirmed(false);
                user1.setUsersMail(usersMail);
                return user1;
            case USERS2:
                Users user2 = new Users("login2", "password2");
                user2.setName("name2");
                user2.setSurname("surname2");
                user2.setAdmin(false);
                user2.setPhoneNumber("345762344");
                List<UsersAddress> usersAddressList2 = new ArrayList<>();
                usersAddressList2.add(new UsersAddress("origin2", "destination2"));
                user2.setUsersAddressList(usersAddressList2);
                UsersMail usersMail2 = new UsersMail();
                usersMail2.setMail("mail2");
                usersMail2.setScheduledMail(false);
                usersMail2.generatePinConfirm();
                usersMail2.setMailConfirmed(false);
                user2.setUsersMail(usersMail2);
                return user2;
            case USERS3:
                Users user3 = new Users("login3", "password3");
                user3.setName("name3");
                user3.setSurname("surname3");
                user3.setAdmin(false);
                user3.setPhoneNumber("456546234");
                List<UsersAddress> usersAddressList3 = new ArrayList<>();
                usersAddressList3.add(new UsersAddress("origin3", "destination3"));
                user3.setUsersAddressList(usersAddressList3);
                UsersMail usersMail3 = new UsersMail();
                usersMail3.setMail("mail3");
                usersMail3.setScheduledMail(true);
                usersMail3.generatePinConfirm();
                usersMail3.setMailConfirmed(false);
                user3.setUsersMail(usersMail3);
                return user3;
            default:
                return null;
        }

    }
}
