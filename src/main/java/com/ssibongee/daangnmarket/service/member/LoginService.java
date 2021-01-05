package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.domain.entity.Member;

public interface LoginService {

    public void login(long id);

    public void logout();

    public Member getLoginMember(long id);
}
