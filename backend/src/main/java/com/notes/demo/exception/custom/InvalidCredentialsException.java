package com.notes.demo.exception.custom;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Invalid credentials")
public class InvalidCredentialsException extends BadCredentialsException {
    public InvalidCredentialsException(@Nullable String msg) {
        super(msg);
    }
}
