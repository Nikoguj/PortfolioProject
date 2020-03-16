package com.portfolio.project.controller;

import com.google.gson.Gson;
import com.portfolio.project.domain.user.*;
import com.portfolio.project.mapper.AddressMapper;
import com.portfolio.project.mapper.MailMapper;
import com.portfolio.project.mapper.UsersMapper;
import com.portfolio.project.service.EmailService;
import com.portfolio.project.service.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersMapper usersMapper;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AddressMapper addressMapper;

    @MockBean
    private EmailService emailService;

    @MockBean
    private MailMapper mailMapper;

    @Test
    public void createUser() throws Exception {
        //Given
        Users users = new Users();
        users.setLogin("login1");
        users.setPassword("password1");
        UsersMail usersMail = new UsersMail();
        usersMail.setMail("mail1");
        users.setUsersMail(usersMail);

        UsersDto usersDto = new UsersDto();
        usersDto.setLogin("login1");
        usersDto.setPassword("password1");
        UsersMailDto usersMailDto = new UsersMailDto();
        usersMailDto.setMail("mail1");
        usersDto.setUsersMailDto(usersMailDto);

        when(usersMapper.mapToUsers(usersDto)).thenReturn(users);
        when(usersMapper.mapToUsersDto(users)).thenReturn(usersDto);
        when(usersService.saveUser(users, "mail1")).thenReturn(users);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(usersDto);

        ArgumentCaptor<UsersDto> usersDtoArgumentCaptor = ArgumentCaptor.forClass(UsersDto.class);
        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        //When & Then
        mockMvc.perform(post("/v1/user/createUser/" + "mail1").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)).
                andExpect(status().isOk());
        verify(usersMapper, times(1)).mapToUsers(usersDtoArgumentCaptor.capture());
        verify(usersMapper, times(1)).mapToUsersDto(usersArgumentCaptor.capture());
        verify(usersService, times(1)).saveUser(usersArgumentCaptor.capture(), anyString());
    }

    @Test
    public void login() throws Exception {
        //Given
        LoginStatusDto loginStatusDto = new LoginStatusDto("ok", "sessionKey", 1L);

        when(usersService.loginUser("login1", "password1")).thenReturn(loginStatusDto);
        //When
        mockMvc.perform(get("/v1/user/login/" + "login1/" + "password1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("ok")))
                .andExpect(jsonPath("sessionKey", is("sessionKey")))
                .andExpect(jsonPath("userID", is(1)));
    }

    @Test
    public void updateUser() throws Exception {
        //Given

        UsersDto usersDto = new UsersDto();
        usersDto.setLogin("login1");
        usersDto.setPassword("password1");
        usersDto.setPhoneNumber("123456123");
        UsersMailDto usersMailDto = new UsersMailDto();
        usersMailDto.setMail("mail1");
        usersDto.setUsersMailDto(usersMailDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(usersDto);

        //When & Then
        mockMvc.perform(put("/v1/user/updateUser/").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
                .param("userSessionKey", "sessionKey")
                .param("userId", String.valueOf(1L)))
                .andExpect(status().isOk());
    }

}