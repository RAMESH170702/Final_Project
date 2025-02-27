package com.bej.movieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Movie Already Exists")
public class MovieAlreadyExistsException extends Exception {

}

