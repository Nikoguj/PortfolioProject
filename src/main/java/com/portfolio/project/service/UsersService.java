package com.portfolio.project.service;

import com.portfolio.project.domain.user.SessionKey;
import com.portfolio.project.domain.user.Users;
import com.portfolio.project.domain.user.UsersMail;
import com.portfolio.project.repository.user.SessionKeyRepository;
import com.portfolio.project.repository.user.UserRepository;
import com.portfolio.project.repository.user.UsersAddressRepository;
import com.portfolio.project.repository.user.UsersMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Autowired
    private UsersMailRepository mailRepository;

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    public Users saveUser(final Users users, final String mail) {
        try {
            users.setCreateDate(new Date());
            UsersMail usersMail = new UsersMail();
            usersMail.setMail(mail);
            usersMail.setUsers(users);
            usersMail.setMailConfirmed(false);
            usersMail.generatePinConfirm();
            users.setUsersMail(usersMail);
            return userRepository.save(users);
        } catch (Exception e) {
            Users users1 = new Users();
            users1.setCreateDate(new Date());
            UsersMail usersMail = new UsersMail();
            usersMail.setMail(mail);
            usersMail.setUsers(users1);
            usersMail.setMailConfirmed(false);
            usersMail.generatePinConfirm();
            users1.setUsersMail(usersMail);
            return users1;
        }
    }

    public String loginUser(final String login, final String password) {
        Optional<Users> users = userRepository.findByLogin(login);
        if (users.isPresent()) {
            if (users.get().getPassword().equals(password)) {
                try {
                    sessionKeyRepository.deleteByUsers(users.get());
                } catch (Exception e) {

                }
                users.get().setLastLogin(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                SessionKey sessionKey = new SessionKey();
                sessionKey.generateSessionKey();
                sessionKey.setUsers(users.get());
                sessionKey.setTermOfValidity();
                users.get().setSessionKey(sessionKey);
                userRepository.save(users.get());
                return sessionKey.generateSessionKey();
            } else {
                return "invalid password";
            }
        } else {
            return "invalid login";
        }
    }

    public Users updateUser(final Users users) {
        Users usersInDatabase = userRepository.findByLogin(users.getLogin()).get();
        if(users.getPassword() != null) {
            usersInDatabase.setPassword(users.getPassword());
        }

        if(users.getName() != null) {
            usersInDatabase.setName(users.getName());
        }

        if(users.getSurname() != null) {
            usersInDatabase.setSurname(users.getSurname());
        }

        if(users.getPhoneNumber() != null) {
            usersInDatabase.setPhoneNumber(users.getPhoneNumber());
        }
        userRepository.save(usersInDatabase);
        return usersInDatabase;
    }
}
