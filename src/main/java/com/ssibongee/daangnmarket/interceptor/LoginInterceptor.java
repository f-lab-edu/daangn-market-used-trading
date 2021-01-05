package com.ssibongee.daangnmarket.interceptor;

import com.ssibongee.daangnmarket.advice.exception.MemberNotFoundException;
import com.ssibongee.daangnmarket.advice.exception.UnAuthorizedAccessException;
import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
import com.ssibongee.daangnmarket.domain.entity.Member;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String MEMBER_ID = "MEMBER_ID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Member member = (Member) request.getSession().getAttribute(MEMBER_ID);

        if (handlerMethod.hasMethodAnnotation(LoginRequired.class) && member == null) {
            throw new UnAuthorizedAccessException();
        }
        return true;
    }
}
