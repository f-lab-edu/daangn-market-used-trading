package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.advice.exception.MemberNotFoundException;
import com.ssibongee.daangnmarket.domain.dto.MemberDto;
import com.ssibongee.daangnmarket.domain.dto.ProfileRequest;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void registrationMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findMemberById(long id) {
        return memberRepository.findMemberById(id).orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public boolean isValidMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        Member member = findMemberByEmail(memberDto.getEmail());

        if (passwordEncoder.matches(memberDto.getPassword(), member.getPassword())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void updateMemberProfile(Member member, ProfileRequest profileRequest) {
        member.update(profileRequest.getNickname());
    }


}
