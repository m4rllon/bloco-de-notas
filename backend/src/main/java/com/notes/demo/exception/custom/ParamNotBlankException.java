package com.notes.demo.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Parameter not be blank")
public class ParamNotBlankException extends RuntimeException {
    public ParamNotBlankException(String message) {
        super(message);
    }
}
