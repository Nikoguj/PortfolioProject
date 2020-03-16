package com.portfolio.project.service;

import com.portfolio.project.domain.user.*;
import com.portfolio.project.exception.AddressNotFoundException;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.factory.UserFactory;
import com.portfolio.project.repository.user.SessionKeyRepository;
import com.portfolio.project.repository.user.UserRepository;
import com.portfolio.project.repository.user.UsersAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Autowired
    private SessionKeyRepository sessionKeyRepository;

    @Autowired
    private UserFactory userFactory;

    public Users saveUser(final Users users, final String mail) {
        users.setAdmin(false);
        try {
            users.setCreateDate(new Date());
            UsersMail usersMail = new UsersMail();
            usersMail.setMail(mail);
            usersMail.setUsers(users);
            usersMail.setMailConfirmed(false);
            usersMail.generatePinConfirm();
            users.setUsersMail(usersMail);
            Optional<Users> readUsers = userRepository.findByUsersMail_Mail(mail);
            if (readUsers.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail Already Exists");
            }
            return userRepository.save(users);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "Create error");
        }
    }

    public LoginStatusDto loginUser(final String login, final String password) {
        Optional<Users> users = userRepository.findByLogin(login);
        if (users.isPresent()) {
            if (users.get().getPassword().equals(password)) {
                try {
                    sessionKeyRepository.deleteByUsers(users.get());
                } catch (Exception ignored) {

                }
                users.get().setLastLogin(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
                SessionKey sessionKey = new SessionKey();
                sessionKey.generateSessionKey();
                sessionKey.setUsers(users.get());
                sessionKey.setTermOfValidity();
                users.get().setSessionKey(sessionKey);
                userRepository.save(users.get());
                return new LoginStatusDto("ok", sessionKey.getSessionKey(), users.get().getId());
            } else {
                return new LoginStatusDto("invalid password", "", (long) 0);
            }
        } else {
            return new LoginStatusDto("invalid login", "", (long) 0);
        }
    }

    public Users updateUser(final Users users, final String userSessionKey, final Long userId) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity())) {
            Users usersInDatabase = userRepository.findByLogin(users.getLogin()).orElseThrow(UserNotFoundException::new);
            if (users.getPassword() != null) {
                usersInDatabase.setPassword(users.getPassword());
            }

            if (users.getName() != null) {
                usersInDatabase.setName(users.getName());
            }

            if (users.getSurname() != null) {
                usersInDatabase.setSurname(users.getSurname());
            }

            if (users.getPhoneNumber() != null) {
                usersInDatabase.setPhoneNumber(users.getPhoneNumber());
            }
            userRepository.save(usersInDatabase);
            return usersInDatabase;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public List<UsersAddress> getAddresses(final String userSessionKey, final Long userId) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity())) {
            return user.getUsersAddressList();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public List<UsersAddress> deleteAddress(final String userSessionKey, final Long userId, final Long addressId) throws UserNotFoundException, AddressNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        UsersAddress address1 = usersAddressRepository.findById(addressId).orElseThrow(AddressNotFoundException::new);
        List<UsersAddress> usersAddress = getAddresses(userSessionKey, userId);
        List<UsersAddress> returnAddress = new ArrayList<>();

        for (UsersAddress address : usersAddress) {
            if (!address.getId().equals(addressId)) {
                returnAddress.add(address);
            }
        }
        user.getUsersAddressList().remove(address1);
        userRepository.save(user);

        return returnAddress;
    }

    public void verifyMail(String pin) throws UserNotFoundException {
        Users user = userRepository.findByUsersMail_PinConfirmMail(pin).orElseThrow(UserNotFoundException::new);
        user.getUsersMail().setMailConfirmed(true);
        userRepository.save(user);
    }

    public UsersMail setDailyMail(String userSessionKey, Long userId, boolean scheduledMail) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity())) {
            user.getUsersMail().setScheduledMail(scheduledMail);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
        return user.getUsersMail();
    }

    public Users setAdmin(String userSessionKey, Long userId, boolean value, Long userIdSet) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Users userSet = userRepository.findById(userIdSet).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            userSet.setAdmin(value);
            userRepository.save(userSet);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
        return userSet;
    }

    public Users blockUser(String userSessionKey, Long userId, boolean value, Long userIdSet) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Users userSet = userRepository.findById(userIdSet).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            userSet.setBlock(value);
            userRepository.save(userSet);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
        return userSet;
    }

    public List<UsersAddress> getAddressesByAdmin(String userSessionKey, Long userId, Long userIdSet) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            Users userSet = userRepository.findById(userIdSet).orElseThrow(UserNotFoundException::new);
            return userSet.getUsersAddressList();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public Users getUserByAdmin(String userSessionKey, Long userId, Long userIdSet) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            Users userSet = userRepository.findById(userIdSet).orElseThrow(UserNotFoundException::new);
            return userSet;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public void removeUser(String userSessionKey, Long userId, Long userIdSet) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            userRepository.deleteById(userIdSet);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public List<Users> getUsersByAdmin(String userSessionKey, Long userId) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            List<Users> usersList = userRepository.findAll();
            return usersList;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }

    public void saveUsersFromFactory(String userSessionKey, Long userId) throws UserNotFoundException {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getSessionKey().getSessionKey().equals(userSessionKey) && LocalDateTime.now().isBefore(user.getSessionKey().getTermOfValidity()) && user.isAdmin()) {
            userRepository.save(userFactory.makeUsers(UserFactory.USERS1));
            userRepository.save(userFactory.makeUsers(UserFactory.USERS2));
            userRepository.save(userFactory.makeUsers(UserFactory.USERS3));
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid SessionKey");
        }
    }
}
