package com.portfolio.project.domain;

import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.repository.user.UsersMailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersMailTest {

    @Autowired
    private UsersMailRepository usersMailRepository;

    @Test
    public void testSaveUsersMailWithoutUser() {
        //Given
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1@mail.com");
        usersMail.generatePinConfirm();

        //When
        usersMailRepository.save(usersMail);
        Long usersMailId = usersMail.getId();
        Optional<UsersMail> optionalUsersMail = usersMailRepository.findById(usersMailId);

        //Then
        Assert.assertTrue(optionalUsersMail.isPresent());
        Assert.assertNotEquals(Optional.of(0L), optionalUsersMail.get().getId());
        Assert.assertNotEquals(0, optionalUsersMail.get().getPinConfirmMail());

        //Clean Up
        try {
            usersMailRepository.deleteById(usersMailId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}