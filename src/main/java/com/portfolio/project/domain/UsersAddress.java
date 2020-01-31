package com.portfolio.project.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Users_Address")
public class UsersAddress {
    private Long id;
    private String City;
    private String ZipCode;
    private String Street;
    private int HouseNumber;
    private int ApartmentNumber;
    private Users users;

    public UsersAddress() {

    }

    public UsersAddress(String city, String zipCode, String street, int houseNumber, int apartmentNumber) {
        City = city;
        ZipCode = zipCode;
        Street = street;
        HouseNumber = houseNumber;
        ApartmentNumber = apartmentNumber;
    }

    public UsersAddress(String city, String zipCode, int houseNumber, int apartmentNumber) {
        City = city;
        ZipCode = zipCode;
        HouseNumber = houseNumber;
        ApartmentNumber = apartmentNumber;
    }

    public UsersAddress(String city, String zipCode, int houseNumber) {
        City = city;
        ZipCode = zipCode;
        HouseNumber = houseNumber;
    }

    public UsersAddress(String city, String zipCode, String street, int houseNumber) {
        City = city;
        ZipCode = zipCode;
        Street = street;
        HouseNumber = houseNumber;
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
    @Column(name = "City")
    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    @NotNull
    @Column(name = "Zip_Code")
    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    @Column(name = "Street")
    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    @NotNull
    @Column(name = "House_Number")
    public int getHouseNumber() {
        return HouseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        HouseNumber = houseNumber;
    }

    @Column(name = "Apartment_Number")
    public int getApartmentNumber() {
        return ApartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        ApartmentNumber = apartmentNumber;
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

        if (HouseNumber != that.HouseNumber) return false;
        if (ApartmentNumber != that.ApartmentNumber) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (City != null ? !City.equals(that.City) : that.City != null) return false;
        if (ZipCode != null ? !ZipCode.equals(that.ZipCode) : that.ZipCode != null) return false;
        if (!Street.equals(that.Street)) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (City != null ? City.hashCode() : 0);
        result = 31 * result + (ZipCode != null ? ZipCode.hashCode() : 0);
        result = 31 * result + Street.hashCode();
        result = 31 * result + HouseNumber;
        result = 31 * result + ApartmentNumber;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }
}
