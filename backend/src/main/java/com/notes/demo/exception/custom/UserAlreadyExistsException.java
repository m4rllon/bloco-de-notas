package com.notes.demo.exception.custom;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Username or Email already exists.")
public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(@Nullable String msg){
        super(msg);
    }
}
