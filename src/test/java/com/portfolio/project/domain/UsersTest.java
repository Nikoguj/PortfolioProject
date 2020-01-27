package com.portfolio.project.domain;

import com.portfolio.project.repository.SessionKeyRepository;
import com.portfolio.project.repository.UserRepository;
import com.portfolio.project.repository.UsersAddressRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersTest {

    private final static String LOGIN = "login1";
    private final static String PASSWORD = "password1";
    private final static String MAIL = "mail1";
    private final static String NAME = "name1";
    private final static String SURNAME = "surname1";
    private final static String PHONE_NUMBER = "345623444";

    private final static String CITY = "city1";
    private final static String ZIP_CODE = "ZipCode1";
    private final static String STREET = "Street1";
    private final static int HOUSE_NUMBER = 8;

    private final static String CITY2 = "city2";
    private final static String ZIP_CODE2 = "ZipCode2";
    private final static String STREET2 = "Street2";
    private final static int HOUSE_NUMBER2 = 261;
    private final static int APARTMENT_NUMBER2 = 2;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Test
    public void testSaveUserWithoutOtherEntity() {
        //Given
        Users users = new Users(LOGIN, PASSWORD, MAIL, NAME, SURNAME, PHONE_NUMBER);

        //When
        userRepository.save(users);
        Long id = users.getId();
        Optional<Users> returnUser = userRepository.findById(id);

        //Then
        Assert.assertTrue(returnUser.isPresent());
        Assert.assertEquals(LOGIN, returnUser.get().getLogin());
        Assert.assertEquals(PASSWORD, returnUser.get().getPassword());
        Assert.assertEquals(MAIL, returnUser.get().getMail());
        Assert.assertEquals(NAME, returnUser.get().getName());
        Assert.assertEquals(SURNAME, returnUser.get().getSurname());
        Assert.assertEquals(PHONE_NUMBER, returnUser.get().getPhoneNumber());
        Assert.assertEquals(false, returnUser.get().isMailConfirmed());
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
        Users user = new Users(LOGIN, PASSWORD, MAIL, NAME, SURNAME, PHONE_NUMBER);
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
        Users user = new Users(LOGIN, PASSWORD, MAIL, NAME, SURNAME, PHONE_NUMBER);
        SessionKey sessionKey = new SessionKey();
        sessionKey.generateSessionKey();
        user.setSessionKey(sessionKey);
        sessionKey.setUsers(user);

        //When
        userRepository.save(user);
        Long userId = user.getId();
        Long sessionKeyId = user.getSessionKey().getId();

        int oldSessionKey = sessionKey.getSessionKey();

        Optional<Users> returnUser = userRepository.findById(userId);
        Optional<SessionKey> returnSessionKey = sessionKeyRepository.findById(sessionKeyId);
        if(returnSessionKey.isPresent() && returnUser.isPresent()) {
            returnSessionKey.get().generateSessionKey();
            returnUser.get().setSessionKey(returnSessionKey.get());
            returnSessionKey.get().setUsers(returnUser.get());
        }

        int newSessionKey = returnSessionKey.get().getSessionKey();

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
        Users users = new Users(LOGIN, PASSWORD, MAIL);
        UsersAddress usersAddress = new UsersAddress(CITY, ZIP_CODE, STREET, HOUSE_NUMBER);

        users.getUsersAddressList().add(usersAddress);
        usersAddress.setUsers(users);

        //When
        userRepository.save(users);
        Long usersID = users.getId();
        Optional<Users> returnUsers = userRepository.findById(usersID);

        //Then
        Assert.assertTrue(returnUsers.isPresent());
        Assert.assertEquals(1, returnUsers.get().getUsersAddressList().size());
        Assert.assertEquals(CITY, returnUsers.get().getUsersAddressList().get(0).getCity());
        Assert.assertEquals(ZIP_CODE, returnUsers.get().getUsersAddressList().get(0).getZipCode());
        Assert.assertEquals(STREET, returnUsers.get().getUsersAddressList().get(0).getStreet());
        Assert.assertEquals(HOUSE_NUMBER, returnUsers.get().getUsersAddressList().get(0).getHouseNumber());

        //Clean up
        try {
            userRepository.deleteById(usersID);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void testSaveUsersAddresses() {
        //Given
        Users users = new Users(LOGIN, PASSWORD, MAIL);
        UsersAddress usersAddress1 = new UsersAddress(CITY, ZIP_CODE, STREET, HOUSE_NUMBER);
        UsersAddress usersAddress2 = new UsersAddress(CITY2, ZIP_CODE2, STREET2, HOUSE_NUMBER2, APARTMENT_NUMBER2);

        users.getUsersAddressList().add(usersAddress1);
        users.getUsersAddressList().add(usersAddress2);
        usersAddress1.setUsers(users);
        usersAddress2.setUsers(users);

        //When
        userRepository.save(users);
        Long usersID = users.getId();
        Optional<Users> returnUsers = userRepository.findById(usersID);

        Long usersAddress1Id = usersAddress1.getId();
        Long usersAddress2Id = usersAddress2.getId();

        //Then
        Assert.assertTrue(returnUsers.isPresent());
        Assert.assertEquals(2, returnUsers.get().getUsersAddressList().size());
        Assert.assertEquals(CITY, returnUsers.get().getUsersAddressList().get(0).getCity());
        Assert.assertEquals(ZIP_CODE, returnUsers.get().getUsersAddressList().get(0).getZipCode());
        Assert.assertEquals(STREET, returnUsers.get().getUsersAddressList().get(0).getStreet());
        Assert.assertEquals(HOUSE_NUMBER, returnUsers.get().getUsersAddressList().get(0).getHouseNumber());
        Assert.assertEquals(CITY2, returnUsers.get().getUsersAddressList().get(1).getCity());
        Assert.assertEquals(ZIP_CODE2, returnUsers.get().getUsersAddressList().get(1).getZipCode());
        Assert.assertEquals(STREET2, returnUsers.get().getUsersAddressList().get(1).getStreet());
        Assert.assertEquals(HOUSE_NUMBER2, returnUsers.get().getUsersAddressList().get(1).getHouseNumber());
        Assert.assertEquals(APARTMENT_NUMBER2, returnUsers.get().getUsersAddressList().get(1).getApartmentNumber());

        //Clean up
        returnUsers = userRepository.findById(usersID);
        Optional<UsersAddress> returnUsersAddress = usersAddressRepository.findById(usersAddress1Id);
        Optional<UsersAddress> returnUsersAddress2 = usersAddressRepository.findById(usersAddress2Id);

        if(returnUsers.isPresent() && returnUsersAddress.isPresent() && returnUsersAddress2.isPresent()) {
            returnUsers.get().getUsersAddressList().remove(returnUsersAddress);
            usersAddressRepository.deleteById(returnUsersAddress.get().getId());
        }

        Assert.assertEquals(1, returnUsers.get().getUsersAddressList().size());
    }
}