package com.ssibongee.daangnmarket.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String state;
    private String city;
    private String town;
}
