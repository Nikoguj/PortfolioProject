package com.portfolio.project.domain.user;

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

    private final static String ORIGIN = "origin1";
    private final static String DESTINATION = "destination1";

    @Autowired
    private UsersAddressRepository usersAddressRepository;

    @Test
    public void testSaveUsersAddressWithoutUser() {
        //Given
        UsersAddress usersAddress = new UsersAddress(ORIGIN, DESTINATION);

        //When
        usersAddressRepository.save(usersAddress);
        Long usersAddressId = usersAddress.getId();
        Optional<UsersAddress> returnUsersAddress = usersAddressRepository.findById(usersAddressId);

        //Then
        Assert.assertTrue(returnUsersAddress.isPresent());
        Assert.assertEquals(ORIGIN, returnUsersAddress.get().getOrigin());
        Assert.assertEquals(DESTINATION, returnUsersAddress.get().getDestination());

        //Clean up
        try {
            usersAddressRepository.deleteById(usersAddressId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}