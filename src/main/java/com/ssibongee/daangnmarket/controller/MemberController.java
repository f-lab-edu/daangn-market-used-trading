package com.ssibongee.daangnmarket.controller;

import com.ssibongee.daangnmarket.domain.entity.member.Member;
import com.ssibongee.daangnmarket.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.RESPONSE_CONFLICT;
import static com.ssibongee.daangnmarket.commons.HttpStatusResponseEntity.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 사용자 회원가입 기능
     * @param member
     * @return
     */
    @PostMapping
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid Member member) {
        memberService.registrationMember(member);
        return RESPONSE_OK;
    }

    /**
     * 사용자 이메일 중복체크 기능
     * @param email
     * @return
     */
    @GetMapping("/duplicated/{email}")
    public ResponseEntity<HttpStatus> isDuplicatedEmail(@PathVariable String email) {
        boolean isDuplicated = memberService.isDuplicatedEmail(email);

        if(isDuplicated) {
            return RESPONSE_CONFLICT;
        }

        return RESPONSE_OK;
    }
}
