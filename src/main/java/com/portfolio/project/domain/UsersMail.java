package com.portfolio.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity(name = "Mail")
public class UsersMail {
    private Long id;
    private String mail;
    private int pinConfirmMail;
    private boolean mailConfirmed;
    private Users users;

    public UsersMail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "user_Mail_Id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "mail", unique = true)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "pin_Confirm_Mail")
    public int getPinConfirmMail() {
        return pinConfirmMail;
    }

    public void setPinConfirmMail(int pinConfirmMail) {
        this.pinConfirmMail = pinConfirmMail;
    }

    @Column(name = "mail_Confirmed")
    public boolean isMailConfirmed() {
        return mailConfirmed;
    }

    public void setMailConfirmed(boolean mailConfirmed) {
        this.mailConfirmed = mailConfirmed;
    }

    @OneToOne(mappedBy = "usersMail")
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int generatePinConfirm() {
        SecureRandom secureRandom = new SecureRandom();

        int max = 1000000;
        int min = 100000;
        int pinConfirmMail = secureRandom.nextInt(max-min)+min;
        this.pinConfirmMail = pinConfirmMail;
        return pinConfirmMail;
    }

    public boolean equalsPinConfirm(int pinConfirmMail) {
        if(this.pinConfirmMail == pinConfirmMail) {
            this.mailConfirmed = true;
            return true;
        } else {
            this.mailConfirmed = false;
            return false;
        }
    }
}
