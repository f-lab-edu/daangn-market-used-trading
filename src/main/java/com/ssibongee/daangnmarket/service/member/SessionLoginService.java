package com.ssibongee.daangnmarket.service.member;

import com.ssibongee.daangnmarket.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final HttpSession httpSession;
    private static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public void login(Member member) {
        httpSession.setAttribute(MEMBER_ID, member);
    }

    @Override
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }

    @Override
    public Member getLoginMember(long id) {
        return (Member) httpSession.getAttribute(MEMBER_ID);
    }
}
