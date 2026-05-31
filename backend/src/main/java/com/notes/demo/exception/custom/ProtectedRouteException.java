package com.notes.demo.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Protected route.")
public class ProtectedRouteException extends IOException {
    public ProtectedRouteException(String message) {
        super(message);
    }
}
