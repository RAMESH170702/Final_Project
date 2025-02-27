package com.bej.moviefavoriteservice.service;

import com.bej.moviefavoriteservice.MovieFavoriteServiceApplication;
import com.bej.moviefavoriteservice.domain.Favorite;
import com.bej.moviefavoriteservice.domain.Movie;
import com.bej.moviefavoriteservice.domain.MovieDetails;
import com.bej.moviefavoriteservice.exception.MovieNotFoundException;
import com.bej.moviefavoriteservice.exception.UserAlreadyExistsException;
import com.bej.moviefavoriteservice.exception.UserNotFoundException;
import com.bej.moviefavoriteservice.proxy.MovieProxy;
import com.bej.moviefavoriteservice.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class FavoriteServiceImpl implements FavoriteService{
    private FavoriteRepository favoriteRepository;
    private MovieProxy movieProxy;
    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, MovieProxy movieProxy) {
        this.favoriteRepository = favoriteRepository;
        this.movieProxy = movieProxy;
    }
    @Override
    public List<MovieDetails> getAllFavoriteMovies(String userEmail) throws UserNotFoundException{
        if (favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        Favorite favorite = favoriteRepository.findById(userEmail).get();
        List<Long> movieIds = favorite.getMovieIds();

        List<MovieDetails> movieDetailsList = new ArrayList<>();
        for(Long movieId : movieIds) {
            ResponseEntity<MovieDetails> responseEntity = movieProxy.getMovieById(movieId);
            MovieDetails movie = responseEntity.getBody();
            movieDetailsList.add(movie);
        }
        return movieDetailsList;
    }
    @Override
    public Favorite saveUser(String userEmail) throws UserAlreadyExistsException {
        if(favoriteRepository.findById(userEmail).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Favorite favorite = new Favorite(userEmail, new ArrayList<>());

        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite saveToFavorites(String userEmail, Long movieId) throws UserNotFoundException {
        if(favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        Favorite favorite = favoriteRepository.findById(userEmail).get();

        if(favorite.getMovieIds().isEmpty()) {
            favorite.setMovieIds(Arrays.asList(movieId));
        }
        else {
            List<Long> movies = favorite.getMovieIds();
            if (!movies.contains(movieId)) {
                movies.add(movieId);
                favorite.setMovieIds(movies);
            }
        }
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite deleteFromFavorites(String userEmail, Long movieId) throws UserNotFoundException, MovieNotFoundException {
        if(favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(userEmail);
        Favorite favorite = optionalFavorite.get();
        if (favorite.getMovieIds() == null){
            throw new MovieNotFoundException();
        }
        List<Long> movies = favorite.getMovieIds();
        movies.remove(movieId);
        favorite.setMovieIds(movies);
        return favoriteRepository.save(favorite);
    }

    @Override
    public boolean checkFavoriteStatus(String userEmail, Long movieId) throws UserNotFoundException{
        if(favoriteRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(userEmail);
        Favorite favorite = optionalFavorite.get();
        List<Long> movies = favorite.getMovieIds();
        return movies.contains(movieId);
    }
}
