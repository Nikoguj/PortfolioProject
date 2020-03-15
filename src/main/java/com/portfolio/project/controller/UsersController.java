package com.portfolio.project.controller;

import com.portfolio.project.domain.user.UsersDto;
import com.portfolio.project.mapper.UsersMapper;
import com.portfolio.project.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @PostMapping("createUser/{mail}")
    public UsersDto createUser(@RequestBody UsersDto usersDto, @PathVariable String mail) {
        return usersMapper.mapToUsersDto(usersService.saveUser(usersMapper.mapToUsers(usersDto), mail));
    }

    @GetMapping("login/{login}/{password}")
    public String login(@PathVariable String login, @PathVariable String password) {
        return usersService.loginUser(login, password);
    }

    @PutMapping("updateUser")
    public UsersDto updateUser(@RequestBody UsersDto usersDto) {
        return usersMapper.mapToUsersDto(usersService.updateUser(usersMapper.mapToUsers(usersDto)));
    }
}
