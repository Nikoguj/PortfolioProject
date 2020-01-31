package com.portfolio.project.domain;

import com.portfolio.project.domain.user.UsersAddress;
import com.portfolio.project.repository.user.UsersAddressRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersAddressTest {

    private final static String LOGIN = "login1";
    private final static String PASSWORD = "password1";
    private final static String MAIL = "mail1";

    private final static String CITY = "city1";
    private final static String ZIP_CODE = "ZipCode1";
    private final static String STREET = "Street1";
    private final static int HOUSE_NUMBER = 8;
    private final static int APARTMENT_NUMBER = 2;

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Test
    public void testSaveUsersAddressWithoutUser() {
        //Given
        UsersAddress usersAddress = new UsersAddress(CITY, ZIP_CODE, HOUSE_NUMBER);
        usersAddress.setStreet(STREET);
        usersAddress.setApartmentNumber(APARTMENT_NUMBER);

        //When
        usersAddressRepository.save(usersAddress);
        Long usersAddressId = usersAddress.getId();
        Optional<UsersAddress> returnUsersAddress = usersAddressRepository.findById(usersAddressId);

        //Then
        Assert.assertTrue(returnUsersAddress.isPresent());
        Assert.assertEquals(CITY, returnUsersAddress.get().getCity());
        Assert.assertEquals(ZIP_CODE, returnUsersAddress.get().getZipCode());
        Assert.assertEquals(STREET, returnUsersAddress.get().getStreet());
        Assert.assertEquals(HOUSE_NUMBER, returnUsersAddress.get().getHouseNumber());
        Assert.assertEquals(APARTMENT_NUMBER, returnUsersAddress.get().getApartmentNumber());

        //Clean up
        try {
            usersAddressRepository.deleteById(usersAddressId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}