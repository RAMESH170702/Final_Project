package com.bej.moviefavoriteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "Movie Not Found")
public class MovieNotFoundException extends Exception{
}
