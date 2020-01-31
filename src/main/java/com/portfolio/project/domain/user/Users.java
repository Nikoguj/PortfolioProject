package com.portfolio.project.domain.user;

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
    private String name;
    private String surname;
    private String phoneNumber;
    private boolean phoneNumberConfirmed;
    private Date createDate;
    private Date lastLogin;
    private SessionKey sessionKey;
    private List<UsersAddress> usersAddressList = new ArrayList<>();
    private UsersMail usersMail;

    public Users() {
        this.phoneNumberConfirmed = false;
        this.createDate = new Date();
    }

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
        this.phoneNumberConfirmed = false;
        this.createDate = new Date();
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
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval=true
    )
    public List<UsersAddress> getUsersAddressList() {
        return usersAddressList;
    }

    public void setUsersAddressList(List<UsersAddress> usersAddressList) {
        this.usersAddressList = usersAddressList;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_Mail_Id", referencedColumnName = "user_Mail_Id")
    public UsersMail getUsersMail() {
        return usersMail;
    }

    public void setUsersMail(UsersMail usersMail) {
        this.usersMail = usersMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (id != null ? !id.equals(users.id) : users.id != null) return false;
        return login != null ? login.equals(users.login) : users.login == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
