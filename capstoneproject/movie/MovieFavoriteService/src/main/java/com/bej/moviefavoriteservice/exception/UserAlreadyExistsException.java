package com.bej.moviefavoriteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "User already exists")
public class UserAlreadyExistsException extends Exception{
}
