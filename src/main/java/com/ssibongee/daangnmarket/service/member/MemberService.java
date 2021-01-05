package com.ssibongee.daangnmarket.service.member;


import com.ssibongee.daangnmarket.domain.dto.MemberDto;
import com.ssibongee.daangnmarket.domain.dto.ProfileRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MemberService {

    public void registrationMember(Member member);

    public boolean isDuplicatedEmail(String email);

    public Member findMemberByEmail(String email);

    public Member findMemberById(long id);

    public boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

    public void updateMemberProfile(Member member, ProfileRequest profileRequest);
}
