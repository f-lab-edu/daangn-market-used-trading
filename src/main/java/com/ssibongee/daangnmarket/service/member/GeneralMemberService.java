package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.domain.entity.member.Member;
import com.ssibongee.daangnmarket.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void registrationMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
