package com.ssibongee.daangnmarket.domain.dto;

import com.ssibongee.daangnmarket.domain.entity.Address;
import com.ssibongee.daangnmarket.domain.entity.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class LocationAddressRequest {

    private final String state;
    private final String city;
    private final String town;

    private final Double longitude;
    private final Double latitude;

    public Address toAddress() {
        return Address.builder()
                .state(state)
                .city(city)
                .town(town)
                .build();
    }

    public Location toLocation() {
        return Location.builder()
                .longitude(longitude)
                .latitude(latitude)
                .build();
    }
}
