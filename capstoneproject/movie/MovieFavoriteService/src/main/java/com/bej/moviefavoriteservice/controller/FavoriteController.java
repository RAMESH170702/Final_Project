package com.bej.moviefavoriteservice.controller;

import com.bej.moviefavoriteservice.domain.Favorite;
import com.bej.moviefavoriteservice.domain.User;
import com.bej.moviefavoriteservice.exception.MovieNotFoundException;
import com.bej.moviefavoriteservice.exception.UserAlreadyExistsException;
import com.bej.moviefavoriteservice.exception.UserNotFoundException;
import com.bej.moviefavoriteservice.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v3/favorites")
public class FavoriteController {
    private FavoriteService favoriteService;
    private ResponseEntity responseEntity;
    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        try{
            responseEntity = new ResponseEntity<>(favoriteService.saveUser(user.getUserEmail()), HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e)
        {
            throw new UserAlreadyExistsException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/favorite/user/{userEmail}")
    public ResponseEntity<?> getAllFavoriteMovies(@PathVariable String userEmail) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(favoriteService.getAllFavoriteMovies(userEmail), HttpStatus.OK);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/favorite/user/{userEmail}/movie/{movieId}")
    public ResponseEntity<?> saveToFavorites(@PathVariable String userEmail, @PathVariable Long movieId) throws UserNotFoundException {
        try{
            responseEntity = new ResponseEntity<>(favoriteService.saveToFavorites(userEmail, movieId), HttpStatus.CREATED);
        }
        catch (UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("/favorite/user/{userEmail}/movie/{movieId}")
    public ResponseEntity<?> deleteFromFavorites(@PathVariable String userEmail, @PathVariable Long movieId) throws MovieNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(favoriteService.deleteFromFavorites(userEmail, movieId), HttpStatus.CREATED);
        }
        catch (MovieNotFoundException e)
        {
            throw new MovieNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/favorite/user/{userEmail}/movie/{movieId}")
    public ResponseEntity<?> checkFavoriteStatus(@PathVariable String userEmail, @PathVariable Long movieId) throws UserNotFoundException {
        try {
            responseEntity = new ResponseEntity<>(favoriteService.checkFavoriteStatus(userEmail, movieId), HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
