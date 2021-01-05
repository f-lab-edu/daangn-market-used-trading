package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.domain.entity.Member;

public interface LoginService {

    public void login(Member member);

    public void logout();

    public Member getLoginMember(long id);
}
