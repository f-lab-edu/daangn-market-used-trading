package com.ssibongee.daangnmarket.domain.dto;

import com.ssibongee.daangnmarket.domain.entity.Address;
import com.ssibongee.daangnmarket.domain.entity.Location;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationAddressRequest {

    private final String depth1;
    private final String depth2;
    private final String depth3;

    private final Double longitude;
    private final Double latitude;

    public Address toAddress() {
        return Address.builder()
                .depth1(depth1)
                .depth2(depth2)
                .depth3(depth3)
                .build();
    }

    public Location toLocation() {
        return Location.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}
