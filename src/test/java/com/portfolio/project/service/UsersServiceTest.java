package com.portfolio.project.service;

import com.portfolio.project.domain.user.*;
import com.portfolio.project.exception.AddressNotFoundException;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.repository.user.SessionKeyRepository;
import com.portfolio.project.repository.user.UserRepository;
import com.portfolio.project.repository.user.UsersAddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {
    @InjectMocks
    private UsersService usersService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UsersAddressRepository usersAddressRepository;

    @Mock
    private SessionKeyRepository sessionKeyRepository;

    @Test
    public void saveUser() {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);

        when(userRepository.save(users)).thenReturn(users);

        //When
        Users returnUser = usersService.saveUser(users, "mail1");

        //Then
        Assert.assertNotNull(returnUser);
        Assert.assertEquals(new Long(1L), returnUser.getId());
        Assert.assertEquals("login1", returnUser.getLogin());
        Assert.assertEquals("password1", returnUser.getPassword());
        Assert.assertEquals("mail1", returnUser.getUsersMail().getMail());
    }

    @Test
    public void loginUser() {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setLastLogin(Date.from(LocalDateTime.of(2000, 2, 2, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);

        when(userRepository.findByLogin("login1")).thenReturn(java.util.Optional.of(users));
        when(userRepository.save(users)).thenReturn(users);

        //When
        LoginStatusDto returnLoginStatusDto = usersService.loginUser("login1", "password1");

        //Then
        Assert.assertNotNull(returnLoginStatusDto);
        Assert.assertEquals("ok", returnLoginStatusDto.getStatus());
        Assert.assertNotEquals("", returnLoginStatusDto.getSessionKey());
        Assert.assertEquals(java.util.Optional.of(1L).get(), java.util.Optional.ofNullable(returnLoginStatusDto.getUserID()).get());


    }

    @Test
    public void updateUser() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users newUsers = new Users();
        newUsers.setId(1L);
        newUsers.setLogin("login1");
        newUsers.setPassword("password1");
        newUsers.setName("name1");
        newUsers.setSurname("surname1");
        UsersMail newUsersMail = new UsersMail();
        newUsersMail.setMail("mail1");
        newUsers.setUsersMail(usersMail);
        SessionKey newSessionKey = new SessionKey();
        newSessionKey.setSessionKey("sessionKey");
        newSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        newUsers.setSessionKey(newSessionKey);

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(users));
        when(userRepository.findByLogin("login1")).thenReturn(java.util.Optional.of(users));

        //When
        Users returnUsers = usersService.updateUser(newUsers, "sessionKey", 1L);

        //Then
        Assert.assertNotNull(returnUsers);
        Assert.assertEquals("name1", returnUsers.getName());
        Assert.assertEquals("surname1", returnUsers.getSurname());
    }

    @Test
    public void getAddresses() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        List<UsersAddress> usersAddressList = new ArrayList<>();
        UsersAddress usersAddress1 = new UsersAddress();
        usersAddress1.setId(1L);
        usersAddress1.setOrigin("origin1");
        usersAddress1.setDestination("destination1");
        usersAddressList.add(usersAddress1);
        UsersAddress usersAddress2 = new UsersAddress();
        usersAddress2.setId(2L);
        usersAddress2.setOrigin("origin2");
        usersAddress2.setDestination("destination2");
        usersAddressList.add(usersAddress2);
        users.setUsersAddressList(usersAddressList);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));

        //When
        List<UsersAddress> usersAddresses = usersService.getAddresses(users.getSessionKey().getSessionKey(), users.getId());

        //Then
        Assert.assertNotNull(usersAddresses);
        Assert.assertEquals(2, usersAddresses.size());
        Assert.assertEquals("origin1", usersAddresses.get(0).getOrigin());
        Assert.assertEquals("origin2", usersAddresses.get(1).getOrigin());
        Assert.assertEquals("destination1", usersAddresses.get(0).getDestination());
        Assert.assertEquals("destination2", usersAddresses.get(1).getDestination());
    }

    @Test
    public void deleteAddress() throws AddressNotFoundException, UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        List<UsersAddress> usersAddressList = new ArrayList<>();
        UsersAddress usersAddress1 = new UsersAddress();
        usersAddress1.setId(1L);
        usersAddress1.setOrigin("origin1");
        usersAddress1.setDestination("destination1");
        usersAddressList.add(usersAddress1);
        UsersAddress usersAddress2 = new UsersAddress();
        usersAddress2.setId(2L);
        usersAddress2.setOrigin("origin2");
        usersAddress2.setDestination("destination2");
        usersAddressList.add(usersAddress2);
        users.setUsersAddressList(usersAddressList);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(usersAddressRepository.findById(1L)).thenReturn(java.util.Optional.of(usersAddress1));

        //When
        List<UsersAddress> usersAddresses = usersService.deleteAddress(users.getSessionKey().getSessionKey(), users.getId(), 1L);

        //Then
        Assert.assertNotNull(usersAddresses);
        Assert.assertEquals(1, usersAddresses.size());
        Assert.assertEquals("origin2", usersAddresses.get(0).getOrigin());
        Assert.assertEquals("destination2", usersAddresses.get(0).getDestination());

    }

    @Test
    public void setDailyMail() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));

        //When
        UsersMail returnUsersMail = usersService.setDailyMail(users.getSessionKey().getSessionKey(), users.getId(), true);

        //Then
        Assert.assertNotNull(returnUsersMail);
        Assert.assertEquals(true, returnUsersMail.isScheduledMail());
    }

    @Test
    public void setAdmin() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(usersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(otherSessionKey);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(userRepository.findById(otherUsers.getId())).thenReturn(java.util.Optional.of(otherUsers));

        //When
        Users returnUsers = usersService.setAdmin(users.getSessionKey().getSessionKey(), users.getId(), true, otherUsers.getId());

        //Then
        Assert.assertNotNull(returnUsers);
        Assert.assertEquals(true, returnUsers.isAdmin());
    }

    @Test
    public void blockUser() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(usersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(otherSessionKey);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(userRepository.findById(otherUsers.getId())).thenReturn(java.util.Optional.of(otherUsers));

        //When
        Users returnUsers = usersService.blockUser(users.getSessionKey().getSessionKey(), users.getId(), true, otherUsers.getId());

        //Then
        Assert.assertNotNull(returnUsers);
        Assert.assertEquals(true, returnUsers.isBlock());
    }

    @Test
    public void getAddressesByAdmin() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(usersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(otherSessionKey);

        List<UsersAddress> usersAddressList = new ArrayList<>();
        UsersAddress usersAddress1 = new UsersAddress();
        usersAddress1.setId(1L);
        usersAddress1.setOrigin("origin1");
        usersAddress1.setDestination("destination1");
        usersAddressList.add(usersAddress1);
        UsersAddress usersAddress2 = new UsersAddress();
        usersAddress2.setId(2L);
        usersAddress2.setOrigin("origin2");
        usersAddress2.setDestination("destination2");
        usersAddressList.add(usersAddress2);
        otherUsers.setUsersAddressList(usersAddressList);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(userRepository.findById(otherUsers.getId())).thenReturn(java.util.Optional.of(otherUsers));

        //When
        List<UsersAddress> returnUsersAddress = usersService.getAddressesByAdmin(users.getSessionKey().getSessionKey(), users.getId(), otherUsers.getId());

        //Then
        Assert.assertNotNull(returnUsersAddress);
        Assert.assertEquals(2, returnUsersAddress.size());
        Assert.assertEquals("origin1", returnUsersAddress.get(0).getOrigin());
        Assert.assertEquals("origin2", returnUsersAddress.get(1).getOrigin());
        Assert.assertEquals("destination1", returnUsersAddress.get(0).getDestination());
        Assert.assertEquals("destination2", returnUsersAddress.get(1).getDestination());
    }

    @Test
    public void getUserByAdmin() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(otherUsersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        otherUsers.setSessionKey(otherSessionKey);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(userRepository.findById(otherUsers.getId())).thenReturn(java.util.Optional.of(otherUsers));

        //When
        Users returnUser = usersService.getUserByAdmin(users.getSessionKey().getSessionKey(), users.getId(), 2L);

        //Then
        Assert.assertNotNull(returnUser);
        Assert.assertEquals(new Long(2L), returnUser.getId());
        Assert.assertEquals("login2", returnUser.getLogin());
        Assert.assertEquals("password2", returnUser.getPassword());
        Assert.assertEquals("mail2", returnUser.getUsersMail().getMail());
    }

    @Test
    public void removeUser() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(otherUsersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        otherUsers.setSessionKey(otherSessionKey);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));

        //When & Then
        usersService.removeUser(users.getSessionKey().getSessionKey(), users.getId(), otherUsers.getId());
        verify(userRepository, times(1)).deleteById(otherUsers.getId());
    }

    @Test
    public void getUsersByAdmin() throws UserNotFoundException {
        //Given
        Users users = new Users();
        users.setId(1L);
        users.setLogin("login1");
        users.setPassword("password1");
        users.setAdmin(true);
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);
        SessionKey sessionKey = new SessionKey();
        sessionKey.setSessionKey("sessionKey");
        sessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        users.setSessionKey(sessionKey);

        List<Users> otherUsersList = new ArrayList<>();

        Users otherUsers = new Users();
        otherUsers.setId(2L);
        otherUsers.setLogin("login2");
        otherUsers.setPassword("password2");
        otherUsers.setAdmin(false);
        UsersMail otherUsersMail = new UsersMail();
        otherUsersMail.setMail("mail2");
        otherUsers.setUsersMail(otherUsersMail);
        SessionKey otherSessionKey = new SessionKey();
        otherSessionKey.setSessionKey("sessionKey");
        otherSessionKey.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        otherUsers.setSessionKey(otherSessionKey);

        Users otherUsers2 = new Users();
        otherUsers2.setId(3L);
        otherUsers2.setLogin("login3");
        otherUsers2.setPassword("password3");
        otherUsers2.setAdmin(false);
        UsersMail otherUsersMail2 = new UsersMail();
        otherUsersMail2.setMail("mail3");
        otherUsers2.setUsersMail(otherUsersMail2);
        SessionKey otherSessionKey2 = new SessionKey();
        otherSessionKey2.setSessionKey("sessionKey");
        otherSessionKey2.setTermOfValidity(LocalDateTime.now().plusMonths(2));
        otherUsers2.setSessionKey(otherSessionKey2);

        otherUsersList.add(otherUsers);
        otherUsersList.add(otherUsers2);

        when(userRepository.findById(users.getId())).thenReturn(java.util.Optional.of(users));
        when(userRepository.findAll()).thenReturn(otherUsersList);

        //When
        List<Users> returnUsers = usersService.getUsersByAdmin(users.getSessionKey().getSessionKey(), users.getId());

        //Then
        Assert.assertNotNull(returnUsers);
        Assert.assertEquals(2, returnUsers.size());
        Assert.assertEquals(new Long(2L), returnUsers.get(0).getId());
        Assert.assertEquals("login2", returnUsers.get(0).getLogin());
        Assert.assertEquals("password2", returnUsers.get(0).getPassword());
        Assert.assertEquals("mail2", returnUsers.get(0).getUsersMail().getMail());
        Assert.assertEquals(new Long(3L), returnUsers.get(1).getId());
        Assert.assertEquals("login3", returnUsers.get(1).getLogin());
        Assert.assertEquals("password3", returnUsers.get(1).getPassword());
        Assert.assertEquals("mail3", returnUsers.get(1).getUsersMail().getMail());
    }
}