package com.portfolio.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Users")
public class Users {
    private Long id;
    private String login;
    private String password;
    private String mail;
    private boolean mailConfirmed;
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean phoneNumberConfirmed;
    private Date createDate;
    private Date lastLogin;
    private SessionKey sessionKey;
    private List<UsersAddress> usersAddressList;

    public Users() {
        this.mailConfirmed = false;
        this.phoneNumberConfirmed = false;
        this.createDate = new Date();
        this.usersAddressList = new ArrayList<>();
    }

    public Users(String login, String password, String mail, String name, String surname, String phoneNumber) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.mailConfirmed = false;
        this.phoneNumberConfirmed = false;
        this.createDate = new Date();
        this.usersAddressList = new ArrayList<>();
    }

    public Users(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.mailConfirmed = false;
        this.phoneNumberConfirmed = false;
        this.createDate = new Date();
        this.usersAddressList = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "user_Id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "login", unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @NotNull
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(name = "mail", unique = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "mail_Confirmed")
    public boolean isMailConfirmed() {
        return mailConfirmed;
    }

    public void setMailConfirmed(boolean mailConfirmed) {
        this.mailConfirmed = mailConfirmed;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "phone_Number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "phone_Number_Confirmed")
    public boolean isPhoneNumberConfirmed() {
        return phoneNumberConfirmed;
    }

    public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
        this.phoneNumberConfirmed = phoneNumberConfirmed;
    }

    @NotNull
    @Column(name = "create_Date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "last_Login")
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_Key_Id", referencedColumnName = "session_Key_Id")
    public SessionKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(SessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @OneToMany(mappedBy = "users",
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.REMOVE},
            fetch = FetchType.EAGER,
            targetEntity = UsersAddress.class
    )
    public List<UsersAddress> getUsersAddressList() {
        return usersAddressList;
    }

    public void setUsersAddressList(List<UsersAddress> usersAddressList) {
        this.usersAddressList = usersAddressList;
    }
}
