package com.portfolio.project.domain.user;

import com.portfolio.project.repository.user.SessionKeyRepository;
import com.portfolio.project.repository.user.UserRepository;
import com.portfolio.project.repository.user.UsersAddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTest {

    private final static String LOGIN = "login1";
    private final static String PASSWORD = "password1";
    private final static String NAME = "name1";
    private final static String SURNAME = "surname1";
    private final static String PHONE_NUMBER = "345623444";

    private final static String ORIGIN = "origin1";
    private final static String DESTINATION = "destination1";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Test
    public void testSaveUserWithoutOtherEntity() {
        //Given
        Users users = new Users(LOGIN, PASSWORD);
        users.setName(NAME);
        users.setSurname(SURNAME);
        users.setPhoneNumber(PHONE_NUMBER);

        //When
        userRepository.save(users);
        Long id = users.getId();
        Optional<Users> returnUser = userRepository.findById(id);

        //Then
        Assert.assertTrue(returnUser.isPresent());
        Assert.assertEquals(LOGIN, returnUser.get().getLogin());
        Assert.assertEquals(PASSWORD, returnUser.get().getPassword());
        Assert.assertEquals(NAME, returnUser.get().getName());
        Assert.assertEquals(SURNAME, returnUser.get().getSurname());
        Assert.assertEquals(PHONE_NUMBER, returnUser.get().getPhoneNumber());
        Assert.assertEquals(false, returnUser.get().isPhoneNumberConfirmed());
        Assert.assertNotEquals(0, returnUser.get().getCreateDate());

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testSaveUserWithSessionKey() {
        //Given
        Users user = new Users(LOGIN, PASSWORD);
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setPhoneNumber(PHONE_NUMBER);
        SessionKey sessionKey = new SessionKey();
        sessionKey.generateSessionKey();
        user.setSessionKey(sessionKey);
        sessionKey.setUsers(user);

        //When
        userRepository.save(user);
        Long id = user.getId();
        Optional<Users> returnUser = userRepository.findById(id);

        //Then
        Assert.assertTrue(returnUser.isPresent());
        Assert.assertNotEquals(Optional.of(0), returnUser.get().getSessionKey().getId());

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testSaveUserWithSessionKeyWhenPreviousWasEntered() {
        //Given
        Users user = new Users(LOGIN, PASSWORD);
        user.setName(NAME);
        user.setSurname(SURNAME);
        user.setPhoneNumber(PHONE_NUMBER);
        SessionKey sessionKey = new SessionKey();
        sessionKey.generateSessionKey();
        user.setSessionKey(sessionKey);
        sessionKey.setUsers(user);

        //When
        userRepository.save(user);
        Long userId = user.getId();
        Long sessionKeyId = user.getSessionKey().getId();

        String oldSessionKey = sessionKey.getSessionKey();

        Optional<Users> returnUser = userRepository.findById(userId);
        Optional<SessionKey> returnSessionKey = sessionKeyRepository.findById(sessionKeyId);
        if (returnSessionKey.isPresent() && returnUser.isPresent()) {
            returnSessionKey.get().generateSessionKey();
            returnUser.get().setSessionKey(returnSessionKey.get());
            returnSessionKey.get().setUsers(returnUser.get());
        }

        String newSessionKey = returnSessionKey.get().getSessionKey();

        //Then
        Assert.assertTrue(returnUser.isPresent());
        Assert.assertTrue(returnSessionKey.isPresent());
        Assert.assertNotEquals(oldSessionKey, newSessionKey);

        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void testSaveUsersAddress() {
        //Given
        Users users = new Users(LOGIN, PASSWORD);
        UsersAddress usersAddress = new UsersAddress(ORIGIN, DESTINATION);

        users.getUsersAddressList().add(usersAddress);
        usersAddress.setUsers(users);

        //When
        userRepository.save(users);
        Long usersID = users.getId();
        Optional<Users> returnUsers = userRepository.findById(usersID);

        //Then
        Assert.assertTrue(returnUsers.isPresent());
        Assert.assertEquals(1, returnUsers.get().getUsersAddressList().size());
        Assert.assertEquals(ORIGIN, returnUsers.get().getUsersAddressList().get(0).getOrigin());
        Assert.assertEquals(DESTINATION, returnUsers.get().getUsersAddressList().get(0).getDestination());

        //Clean up
        try {
            userRepository.deleteById(usersID);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testRemoveUsersAddress() {
        //Given
        Users users = new Users(LOGIN, PASSWORD);
        UsersAddress usersAddress1 = new UsersAddress(ORIGIN, DESTINATION);

        usersAddress1.setUsers(users);
        users.getUsersAddressList().add(usersAddress1);

        //When
        userRepository.save(users);
        Long usersID = users.getId();
        Long usersAddressId = usersAddress1.getId();

        //Then & Clean up
        Optional<Users> returnUsers = userRepository.findById(usersID);
        Optional<UsersAddress> returnAddress = usersAddressRepository.findById(usersAddressId);

        Assert.assertTrue(returnUsers.isPresent());
        Assert.assertTrue(returnAddress.isPresent());
        Assert.assertEquals(1, returnUsers.get().getUsersAddressList().size());

        returnUsers.get().getUsersAddressList().remove(returnAddress.get());
        Assert.assertEquals(0, returnUsers.get().getUsersAddressList().size());
        userRepository.save(returnUsers.get());

        Optional<Users> returnUsers2 = userRepository.findById(users.getId());
        Assert.assertTrue(returnUsers2.isPresent());
        Assert.assertEquals(0, returnUsers2.get().getUsersAddressList().size());

        userRepository.deleteById(usersID);
    }
}