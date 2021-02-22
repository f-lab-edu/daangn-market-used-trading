package com.ssibongee.daangnmarket.member.service;


import com.ssibongee.daangnmarket.member.dto.LocationAddressRequest;
import com.ssibongee.daangnmarket.member.dto.MemberDto;
import com.ssibongee.daangnmarket.member.dto.PasswordRequest;
import com.ssibongee.daangnmarket.member.dto.ProfileRequest;
import com.ssibongee.daangnmarket.member.domain.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;


public interface MemberService {

    public void registrationMember(Member member);

    public boolean isDuplicatedEmail(String email);

    public Member findMemberByEmail(String email);

    public Member findMemberById(long id);

    public boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

    public boolean isValidPassword(Member member, PasswordRequest passwordRequest, PasswordEncoder passwordEncoder);

    public void updateMemberProfile(Member member, ProfileRequest profileRequest);

    public void updateMemberPassword(Member member, PasswordRequest passwordRequest, PasswordEncoder passwordEncoder);

    public void setMemberLocationAddress(Member member, LocationAddressRequest locationAddressRequest);
}
