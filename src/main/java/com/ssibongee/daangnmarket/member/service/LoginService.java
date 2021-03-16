package com.ssibongee.daangnmarket.member.service;

import com.ssibongee.daangnmarket.member.domain.entity.Member;

public interface LoginService {

    public void login(long id);

    public void logout();

    public Member getLoginMember();

    public Long getLoginMemberId();
}
