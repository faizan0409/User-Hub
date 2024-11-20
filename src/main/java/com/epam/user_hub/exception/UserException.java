package com.epam.user_hub.exception;

import org.springframework.http.HttpStatus;

public class UserException extends RuntimeException {
    private HttpStatus httpStatus;
    public UserException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
