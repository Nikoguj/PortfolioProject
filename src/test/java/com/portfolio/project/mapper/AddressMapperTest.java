package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.UsersAddress;
import com.portfolio.project.domain.user.UsersAddressDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AddressMapperTest {

    @Test
    public void mapToListAddressDto() {
        //Given
        AddressMapper addressMapper = new AddressMapper();

        List<UsersAddress> usersAddressList = new ArrayList<>();
        usersAddressList.add(new UsersAddress("origin1", "destination1"));
        usersAddressList.add(new UsersAddress("origin2", "destination2"));
        usersAddressList.add(new UsersAddress("origin3", "destination3"));

        //When
        List<UsersAddressDto> returnAddresses = addressMapper.mapToListAddressDto(usersAddressList);

        //Then
        Assert.assertNotNull(returnAddresses);
        Assert.assertEquals("origin1", returnAddresses.get(0).getOrigin());
        Assert.assertEquals("origin2", returnAddresses.get(1).getOrigin());
        Assert.assertEquals("origin3", returnAddresses.get(2).getOrigin());
        Assert.assertEquals("destination1", returnAddresses.get(0).getDestination());
        Assert.assertEquals("destination2", returnAddresses.get(1).getDestination());
        Assert.assertEquals("destination3", returnAddresses.get(2).getDestination());
    }
}