package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.advice.exception.MemberNotFoundException;
import com.ssibongee.daangnmarket.domain.dto.MemberDto;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.domain.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    private GeneralMemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MemberDto memberDto;

    private Member member;

    @BeforeEach
    void setUp() {
        MemberDto memberDto = MemberDto.builder()
                .email("daangnmarket@admin.com")
                .password("1q2w3e4r!")
                .nickname("김당근")
                .build();

        member = MemberDto.toEntity(memberDto, passwordEncoder);
    }

    @Test
    @DisplayName("중복된 이메일이 존재하지 않는 경우")
    void isNotDuplicatedEmailExist() {
        // given
        when(memberRepository.existsByEmail(any())).thenReturn(false);

        // then
        assertFalse(memberService.isDuplicatedEmail(member.getEmail()));
    }

    @Test
    @DisplayName("중복된 이메일이 존재하는 경우")
    void isDuplicatedEmailExist() {
        // given
        when(memberRepository.existsByEmail(any())).thenReturn(true);

        // then
        assertTrue(memberService.isDuplicatedEmail(member.getEmail()));
    }

    @Test
    @DisplayName("해당 이메일로 가입된 회원이 존재하는 경우")
    void isExistMemberFindByEmail() {
        // given
        when(memberRepository.findMemberByEmail(any())).thenReturn(Optional.of(member));

        // when
        Member findByEmailMember = memberService.findMemberByEmail(member.getEmail());

        // then
        assertThat(findByEmailMember).isNotNull();
        assertThat(findByEmailMember.getId()).isEqualTo(member.getId());
        assertThat(findByEmailMember.getEmail()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("해당 이메일로 가입된 회원이 존재하지 않는 경우")
    void isNotExistMemberFindByEmail() {
        // given
        when(memberRepository.findMemberByEmail(any())).thenReturn(Optional.empty());

        // then
        assertThrows(MemberNotFoundException.class, () -> {
            Member findByEmailMember = memberService.findMemberByEmail(member.getEmail());
        });
    }

}