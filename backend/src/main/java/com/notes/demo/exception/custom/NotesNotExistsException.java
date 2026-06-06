package com.notes.demo.exception.custom;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Notes not found.")
public class NotesNotExistsException extends RuntimeException {
    public NotesNotExistsException(@Nullable String message) {
        super(message);
    }
}
