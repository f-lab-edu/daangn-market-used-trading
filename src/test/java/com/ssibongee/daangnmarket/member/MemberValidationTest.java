package com.ssibongee.daangnmarket.member;

import com.ssibongee.daangnmarket.member.dto.MemberDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    @DisplayName("사용자가 정상적인 이메일과 패스워드를 입력한 경우 정상적으로 유효성 검사에 통과한다")
    void isCorrectValidation() {
        MemberDto memberDto = MemberDto.builder()
                .email("daangn@admin.com")
                .password("D1q2w3e4r!")
                .nickname("김당근")
                .build();

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("사용자의 이메일 형식이 올바르지 않을 경우 유효성 검사에 실패한다.")
    void isNotValidEmail() {
        MemberDto memberDto = MemberDto.builder()
                .email("123@admin/com")
                .password("D1q2w3e4r!")
                .nickname("김당근")
                .build();

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals("유효하지 않은 이메일 형식입니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("사용자의 패스워드 형식이 올바르지 않을 경우 유효성 검사에 실패한다.")
    void isNotValidPassword() {
        MemberDto memberDto = MemberDto.builder()
                .email("123@admin.com")
                .password("1q2w3e4r!")
                .nickname("김당근")
                .build();

        Set<ConstraintViolation<MemberDto>> violations = validator.validate(memberDto);
        assertEquals("최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
                violations.iterator().next().getMessage());
    }
}