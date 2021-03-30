package com.ssibongee.daangnmarket.member.exception;

public class PasswordNotMatchedException extends RuntimeException {

    public PasswordNotMatchedException() {
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }

    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }
}
