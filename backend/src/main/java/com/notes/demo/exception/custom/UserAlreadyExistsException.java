package com.notes.demo.exception.custom;

import org.jspecify.annotations.Nullable;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(@Nullable String msg){
        super(msg);
    }
}
