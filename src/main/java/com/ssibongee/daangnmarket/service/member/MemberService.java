package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.domain.entity.member.Member;

public interface MemberService {

    public void registrationMember(Member member);

    public boolean isDuplicatedEmail(String email);
}
