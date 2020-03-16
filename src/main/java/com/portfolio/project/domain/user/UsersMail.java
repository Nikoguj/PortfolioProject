package com.portfolio.project.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "Mail")
public class UsersMail {
    private Long id;
    private String mail;
    private String pinConfirmMail;
    private boolean mailConfirmed;
    private boolean scheduledMail;
    private Users users;

    public UsersMail() {
    }

    public UsersMail( Long id, String mail, boolean mailConfirmed, boolean scheduledMail) {
        this.id = id;
        this.mail = mail;
        this.mailConfirmed = mailConfirmed;
        this.scheduledMail = scheduledMail;
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
    public String getPinConfirmMail() {
        return pinConfirmMail;
    }

    public void setPinConfirmMail(String pinConfirmMail) {
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

    public String generatePinConfirm() {
        UUID uuid = UUID.randomUUID();
        this.pinConfirmMail = uuid.toString();
        return uuid.toString();
    }

    @Column(name = "scheduled_mail")
    public boolean isScheduledMail() {
        return scheduledMail;
    }

    public void setScheduledMail(boolean sendScheduledMail) {
        this.scheduledMail = sendScheduledMail;
    }
}
