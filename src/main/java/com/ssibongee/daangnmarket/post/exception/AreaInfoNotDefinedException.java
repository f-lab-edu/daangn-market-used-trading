package com.ssibongee.daangnmarket.post.exception;

public class AreaInfoNotDefinedException extends RuntimeException {

    public AreaInfoNotDefinedException() {
        super();
    }

    public AreaInfoNotDefinedException(String message) {
        super(message);
    }

    public AreaInfoNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
