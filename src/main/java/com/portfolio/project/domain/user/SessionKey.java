package com.portfolio.project.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Session_Key")
public class SessionKey {
    private Long id;
    private String sessionKey;
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
    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
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

    public String generateSessionKey() {
        UUID uuid = UUID.randomUUID();
        this.sessionKey = uuid.toString();
        return uuid.toString();
    }

}
