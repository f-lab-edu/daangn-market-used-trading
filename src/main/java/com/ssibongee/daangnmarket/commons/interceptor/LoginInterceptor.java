package com.ssibongee.daangnmarket.commons.interceptor;

import com.ssibongee.daangnmarket.commons.advice.exception.UnAuthorizedAccessException;
import com.ssibongee.daangnmarket.commons.annotation.LoginRequired;
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
        Long memberId = (Long) request.getSession().getAttribute(MEMBER_ID);

        if (handlerMethod.hasMethodAnnotation(LoginRequired.class) && memberId == null) {
            throw new UnAuthorizedAccessException();
        }
        return true;
    }
}
