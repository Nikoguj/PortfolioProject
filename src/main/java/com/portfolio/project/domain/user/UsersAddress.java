package com.portfolio.project.domain.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Users_Address")
public class UsersAddress {
    private Long id;
    private String Origin;
    private String Destination;
    private Users users;

    public UsersAddress() {

    }

    public UsersAddress(String city, String destination) {
        Origin = city;
        Destination = destination;
    }

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Users_Address_Id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "Origin")
    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    @NotNull
    @Column(name = "Destination")
    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    @ManyToOne()
    @JoinColumn(name = "Users_Id")
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersAddress that = (UsersAddress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (Origin != null ? !Origin.equals(that.Origin) : that.Origin != null) return false;
        if (Destination != null ? !Destination.equals(that.Destination) : that.Destination != null) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (Origin != null ? Origin.hashCode() : 0);
        result = 31 * result + (Destination != null ? Destination.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
