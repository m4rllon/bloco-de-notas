package com.notes.demo.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="User without permission.")
public class UserWithoutPermissionException extends RuntimeException{
}
