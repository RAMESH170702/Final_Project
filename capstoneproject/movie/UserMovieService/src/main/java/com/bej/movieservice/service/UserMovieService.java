package com.bej.movieservice.service;

import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.User;
import com.bej.movieservice.exception.MovieNotFoundException;
import com.bej.movieservice.exception.UserAlreadyExistsException;
import com.bej.movieservice.exception.UserNotFoundException;

import java.util.List;

public interface UserMovieService {
    User registerUser(User user) throws UserAlreadyExistsException;
    User getCurrentUserDetails(String emailId);
    String getUsernameByEmail(String email);
    List<Movie> getAllRecommendedMovies(Long movieId) throws MovieNotFoundException;
}
