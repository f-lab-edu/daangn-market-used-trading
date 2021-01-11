package com.ssibongee.daangnmarket.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Builder
@Getter
@RequiredArgsConstructor
public class ProfileRequest {

    private final String email;
    private final String nickname;

}
