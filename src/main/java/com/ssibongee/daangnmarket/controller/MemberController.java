package com.ssibongee.daangnmarket.controller;

import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.domain.dto.MemberDto;
import com.ssibongee.daangnmarket.domain.dto.PasswordRequest;
import com.ssibongee.daangnmarket.domain.dto.ProfileRequest;
import com.ssibongee.daangnmarket.domain.dto.ProfileResponse;
import com.ssibongee.daangnmarket.domain.entity.Member;
import com.ssibongee.daangnmarket.service.member.LoginService;
import com.ssibongee.daangnmarket.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    /**
     * 사용자 회원가입 기능
     *
     * @param memberDto
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid MemberDto memberDto) {

        // 클라이언트에서 사용자 이메일 중복체크를 수행하지만 API요청에 의한 예외상황에 대비하여 더블체크
        boolean isDuplicated = memberService.isDuplicatedEmail(memberDto.getEmail());

        if (isDuplicated) {
            return RESPONSE_CONFLICT;
        }

        Member member = MemberDto.toEntity(memberDto, passwordEncoder);
        memberService.registrationMember(member);

        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 기능
     *
     * @param email
     * @return
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicated = memberService.isDuplicatedEmail(email);

        if (isDuplicated) {
            return RESPONSE_CONFLICT;
        }
        return RESPONSE_OK;
    }

    /**
     * 사용자 로그인 기능
     *
     * @param memberDto
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody @Valid MemberDto memberDto) {

        boolean isValidMember = memberService.isValidMember(memberDto, passwordEncoder);

        if (isValidMember) {
            loginService.login(memberService.findMemberByEmail(memberDto.getEmail()).getId());
            return RESPONSE_OK;
        }

        return RESPONSE_BAD_REQUEST;
    }

    /**
     * 사용자 로그아웃 기능
     *
     * @return
     */
    @LoginRequired
    @GetMapping("/logout")
    public ResponseEntity<HttpStatus> logout() {
        loginService.logout();
        return RESPONSE_OK;
    }

    /**
     * 사용자 프로필 조회 기능
     *
     * @return
     */
    @LoginRequired
    @GetMapping("/my-profile")
    public ResponseEntity<ProfileResponse> getMemberProfile() {

        Member member = loginService.getLoginMember();

        return ResponseEntity.ok(ProfileResponse.of(member));
    }

    /**
     * 사용자 프로필 정보 업데이트 기능
     * @param profileRequest
     * @return
     */
    @LoginRequired
    @PutMapping("/my-profile")
    public ResponseEntity<ProfileResponse> updateMemberProfile(@RequestBody ProfileRequest profileRequest) {

        Member member = loginService.getLoginMember();

        memberService.updateMemberProfile(member, profileRequest);

        return ResponseEntity.ok(ProfileResponse.of(member));
    }

    @LoginRequired
    @PutMapping("/password")
    public ResponseEntity<HttpStatus> changePassword(@Valid @RequestBody PasswordRequest passwordRequest) {

        Member member = loginService.getLoginMember();

        if(memberService.isValidPassword(member, passwordRequest, passwordEncoder)) {
            memberService.updateMemberPassword(member, passwordRequest, passwordEncoder);
        }

        return RESPONSE_OK;
    }

}
