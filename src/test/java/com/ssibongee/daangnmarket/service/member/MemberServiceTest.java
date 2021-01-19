package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.advice.exception.MemberNotFoundException;
import com.ssibongee.daangnmarket.domain.dto.LocationAddressRequest;
import com.ssibongee.daangnmarket.domain.dto.MemberDto;
import com.ssibongee.daangnmarket.domain.dto.PasswordRequest;
import com.ssibongee.daangnmarket.domain.dto.ProfileRequest;
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

    private PasswordRequest passwordRequest;

    private ProfileRequest profileRequest;

    private LocationAddressRequest locationAddressRequest;

    @BeforeEach
    void setUp() {
        when(passwordEncoder.encode(any())).thenReturn("1q2w3e4r!");
        memberDto = MemberDto.builder()
                .email("daangnmarket@admin.com")
                .password("1q2w3e4r!")
                .nickname("김당근")
                .build();

        passwordRequest = new PasswordRequest("1q2w3e4r!", "1q2w3e4r5t!");

        profileRequest = new ProfileRequest("daangnmarket@admin.com", "김오이");

        locationAddressRequest = LocationAddressRequest.builder()
                .state("서울특별시")
                .city("관악구")
                .town("은천동")
                .longitude(126.94250287828)
                .latitude(37.4853777674734)
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

    @Test
    @DisplayName("사용자의 정보가 유효한 경우")
    void isValidMember() {
        // given
        when(memberRepository.findMemberByEmail(any())).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        // then
        assertTrue(memberService.isValidMember(memberDto, passwordEncoder));
    }

    @Test
    @DisplayName("사용자 정보가 유효하지 않은 경우")
    void isNotValidMember() {
        // given
        when(memberRepository.findMemberByEmail(any())).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        // then
        assertFalse(memberService.isValidMember(memberDto, passwordEncoder));
    }

    @Test
    @DisplayName("변경전 패스워드를 올바르게 입력한 경우")
    void isValidOldPassword() {
        // given
        when(passwordEncoder.matches(any(), any())).thenReturn(true);

        // then
        assertTrue(memberService.isValidPassword(member, passwordRequest, passwordEncoder));
    }

    @Test
    @DisplayName("변경전 패스워드를 틀리게 입력한 경우")
    void isNotValidOldPassword() {
        // given
        when(passwordEncoder.matches(any(), any())).thenReturn(false);

        // then
        assertFalse(memberService.isValidPassword(member, passwordRequest, passwordEncoder));
    }

    @Test
    @DisplayName("사용자 프로필 변경에 성공한 경우")
    void successToUpdateMemberProfile() {
        // when
        memberService.updateMemberProfile(member, profileRequest);

        // then
        assertEquals(member.getNickname(), profileRequest.getNickname());
    }

    @Test
    @DisplayName("사용자 패스워드 변경에 성공한 경우")
    void successToUpdatePassword() {
        // given
        when(passwordEncoder.encode(any())).thenReturn(passwordRequest.getNewPassword());

        // when
        memberService.updateMemberPassword(member, passwordRequest, passwordEncoder);

        // then
        assertEquals(member.getPassword(), passwordRequest.getNewPassword());
    }

    @Test
    @DisplayName("사용자 위치정보 등록에 성공한 경우")
    void successToUpdateMemberLocationAndAddress() {
        // when
        memberService.setMemberLocationAddress(member, locationAddressRequest);

        // then
        assertThat(member.getAddress()).isNotNull();
        assertThat(member.getLocation()).isNotNull();
        assertEquals(member.getAddress().getState(), locationAddressRequest.getState());
        assertEquals(member.getAddress().getCity(), locationAddressRequest.getCity());
        assertEquals(member.getAddress().getTown(), locationAddressRequest.getTown());
        assertEquals(member.getLocation().getLongitude(), locationAddressRequest.getLongitude());
        assertEquals(member.getLocation().getLatitude(), locationAddressRequest.getLatitude());
    }

}