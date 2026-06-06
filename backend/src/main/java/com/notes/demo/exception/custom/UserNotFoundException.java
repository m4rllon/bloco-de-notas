package com.notes.demo.exception.custom;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found.")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(@Nullable String message) {
        super(message);
    }
}
