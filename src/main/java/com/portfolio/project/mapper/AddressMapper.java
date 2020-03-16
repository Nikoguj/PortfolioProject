package com.portfolio.project.mapper;

import com.portfolio.project.domain.user.UsersAddress;
import com.portfolio.project.domain.user.UsersAddressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public List<UsersAddressDto> mapToListAddressDto(final List<UsersAddress> usersAddressList) {
        return usersAddressList.stream()
                .map(t -> new UsersAddressDto(t.getId(), t.getOrigin(), t.getDestination()))
                .collect(Collectors.toList());
    }

}
