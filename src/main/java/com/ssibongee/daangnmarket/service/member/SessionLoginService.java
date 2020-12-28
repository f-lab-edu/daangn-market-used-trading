package com.ssibongee.daangnmarket.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public void login(String email) {
        httpSession.setAttribute(MEMBER_ID, email);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }
}
