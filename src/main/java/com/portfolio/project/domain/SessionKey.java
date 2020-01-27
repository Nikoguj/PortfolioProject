package com.portfolio.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity(name = "Session_Key")
public class SessionKey {
    private Long id;
    private int sessionKey;
    private LocalDateTime termOfValidity;
    private Users users;

    public SessionKey() {
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_Key_Id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "session_Key")
    public int getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(int sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Column(name = "term_Of_Validity")
    public LocalDateTime getTermOfValidity() {
        return termOfValidity;
    }

    public void setTermOfValidity(LocalDateTime termOfValidity) {
        this.termOfValidity = termOfValidity;
    }

    @OneToOne(mappedBy = "sessionKey")
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public int generateSessionKey() {
        SecureRandom secureRandom = new SecureRandom();

        int max = 100000000;
        int min = 10000000;
        int sessionKey = secureRandom.nextInt(max-min)+min;
        this.sessionKey = sessionKey;
        this.termOfValidity = LocalDateTime.now().plusMinutes(30);
        return sessionKey;
    }
}
