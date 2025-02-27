package com.bej.moviefavoriteservice.service;

import com.bej.moviefavoriteservice.domain.Favorite;
import com.bej.moviefavoriteservice.domain.Movie;
import com.bej.moviefavoriteservice.domain.MovieDetails;
import com.bej.moviefavoriteservice.exception.MovieNotFoundException;
import com.bej.moviefavoriteservice.exception.UserAlreadyExistsException;
import com.bej.moviefavoriteservice.exception.UserNotFoundException;

import java.util.List;

public interface FavoriteService {
    Favorite saveUser(String userEmail) throws UserAlreadyExistsException;
    List<MovieDetails> getAllFavoriteMovies(String userEmail) throws UserNotFoundException;
    Favorite saveToFavorites(String userEmail,Long movieId) throws UserNotFoundException;
    Favorite deleteFromFavorites(String userEmail, Long movieId) throws UserNotFoundException, MovieNotFoundException;
    boolean checkFavoriteStatus(String userEmail, Long movieId) throws UserNotFoundException;
}
