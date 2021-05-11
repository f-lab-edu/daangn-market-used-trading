package com.ssibongee.daangnmarket.post.dto;

import com.ssibongee.daangnmarket.post.domain.entity.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressRequest {

    @NotEmpty
    private String state;

    @NotEmpty
    private String city;

    @NotEmpty
    private String town;

    public AddressRequest(String state, String city, String town) {
        this.state = state;
        this.city = city;
        this.town = town;
    }

    public static Address toEntity(AddressRequest address) {
        return Address.builder()
                .state(address.getState())
                .city(address.getCity())
                .town(address.getTown())
                .build();
    }
}
