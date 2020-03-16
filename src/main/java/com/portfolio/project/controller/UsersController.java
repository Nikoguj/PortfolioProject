package com.portfolio.project.controller;

import com.portfolio.project.domain.user.LoginStatusDto;
import com.portfolio.project.domain.user.UsersAddressDto;
import com.portfolio.project.domain.user.UsersDto;
import com.portfolio.project.domain.user.UsersMailDto;
import com.portfolio.project.exception.AddressNotFoundException;
import com.portfolio.project.exception.UserNotFoundException;
import com.portfolio.project.mapper.AddressMapper;
import com.portfolio.project.mapper.MailMapper;
import com.portfolio.project.mapper.UsersMapper;
import com.portfolio.project.service.EmailService;
import com.portfolio.project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MailMapper mailMapper;

    @PostMapping("createUser/{mail}")
    public UsersDto createUser(@RequestBody UsersDto usersDto, @PathVariable String mail) {
        return usersMapper.mapToUsersDto(usersService.saveUser(usersMapper.mapToUsers(usersDto), mail));
    }

    @GetMapping("login/{login}/{password}")
    public LoginStatusDto login(@PathVariable String login, @PathVariable String password) {
        return usersService.loginUser(login, password);
    }

    @PutMapping("updateUser")
    public UsersDto updateUser(@RequestBody UsersDto usersDto, @RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        return usersMapper.mapToUsersDto(usersService.updateUser(usersMapper.mapToUsers(usersDto), userSessionKey, userId));
    }

    @GetMapping("getAddresses")
    public List<UsersAddressDto> userGetAddressDtoList(@RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        return addressMapper.mapToListAddressDto(usersService.getAddresses(userSessionKey, userId));
    }

    @DeleteMapping("deleteAddress")
    public List<UsersAddressDto> userDeleteAddress(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam Long addressId) throws AddressNotFoundException, UserNotFoundException {
        return addressMapper.mapToListAddressDto(usersService.deleteAddress(userSessionKey, userId, addressId));
    }

    @PutMapping("sendVerificationEmail")
    public void sendVerificationEmail(@RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        emailService.sendVerificationEmail(userSessionKey, userId);
    }

    @PutMapping("verifyEmail")
    public void verifyEmail(@RequestParam String userVerifyKey) throws UserNotFoundException {
        usersService.verifyMail(userVerifyKey);
    }

    @GetMapping("sendScheduledMail")
    public UsersMailDto sendScheduledMail(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam boolean scheduledMail) throws UserNotFoundException {
        return mailMapper.mapToUsersMailDto(usersService.setDailyMail(userSessionKey, userId, scheduledMail));
    }

    @PutMapping("setAdmin")
    public UsersDto setAdmin(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam boolean value, @RequestParam Long userIdSet) throws UserNotFoundException {
        return usersMapper.mapToUsersDto(usersService.setAdmin(userSessionKey, userId, value, userIdSet));
    }

    @PutMapping("blockUser")
    public UsersDto blockUser(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam boolean value, @RequestParam Long userIdSet) throws UserNotFoundException {
        return usersMapper.mapToUsersDto(usersService.blockUser(userSessionKey, userId, value, userIdSet));
    }

    @GetMapping("getAddressesByAdmin")
    public List<UsersAddressDto> getAddressesByAdmin(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam Long userIdSet) throws UserNotFoundException {
        return addressMapper.mapToListAddressDto(usersService.getAddressesByAdmin(userSessionKey, userId, userIdSet));
    }

    @GetMapping("getUsersByAdmin")
    public List<UsersDto> getUsersByAdmin(@RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        return usersMapper.mapToUsersDtoList(usersService.getUsersByAdmin(userSessionKey, userId));
    }

    @GetMapping("getUserByAdmin")
    public UsersDto getUserByAdmin(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam Long userIdSet) throws UserNotFoundException {
        return usersMapper.mapToUsersDto(usersService.getUserByAdmin(userSessionKey, userId, userIdSet));
    }

    @DeleteMapping("removeUserByAdmin")
    public void removeUser(@RequestParam String userSessionKey, @RequestParam Long userId, @RequestParam Long userIdSet) throws UserNotFoundException {
        usersService.removeUser(userSessionKey, userId, userIdSet);
    }

    @PostMapping("addUserFromFactory")
    public void removeUser(@RequestParam String userSessionKey, @RequestParam Long userId) throws UserNotFoundException {
        usersService.saveUsersFromFactory(userSessionKey, userId);
    }

}
