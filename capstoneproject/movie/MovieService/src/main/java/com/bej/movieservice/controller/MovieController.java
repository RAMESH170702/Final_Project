package com.bej.movieservice.controller;

import com.bej.movieservice.domain.CastMember;
import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.MovieDetails;
import com.bej.movieservice.service.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v4/movies")
public class MovieController {

    private ExternalAPIService externalAPIService;

    @Autowired
    public MovieController(ExternalAPIService externalAPIService) {
        this.externalAPIService = externalAPIService;
    }

    @GetMapping("/genre")
    public ResponseEntity<?> getGenre(){
         externalAPIService.findGenre();
        return ResponseEntity.ok("Genre retreived successfully!");
    }
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<MovieDetails> getMovieById(@PathVariable Long movieId)  {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(externalAPIService.getMovieById(movieId), HttpStatus.OK);
        }

        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/{movieTitle}")
    public ResponseEntity<?> getMovieByTitle(@PathVariable String movieTitle) {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<>(externalAPIService.getMovieByTitle(movieTitle), HttpStatus.OK);
        }

        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/page/{pageId}")
    public ResponseEntity<?> getALlMovies(@PathVariable int pageId) {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Movie>>(externalAPIService.fetchAllMovies(pageId), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    @GetMapping("/movie/recommendations/{movieId}")
    public ResponseEntity<List<Movie>> getMovieRecommendationsById(@PathVariable Long movieId) {
        ResponseEntity<List<Movie>> responseEntity;

        try {
            responseEntity = new ResponseEntity<List<Movie>>(externalAPIService.getMovieRecommendationsById(movieId), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<List<Movie>>(new ArrayList<Movie>(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/movie/cast/{movieId}")
    public ResponseEntity<?> getMovieCast(@PathVariable Long movieId) {
        ResponseEntity responseEntity;

        try {
            responseEntity = new ResponseEntity<>(externalAPIService.getMovieCast(movieId), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/movie/video/{movieId}")
    public ResponseEntity<?> getMovieVideo(@PathVariable Long movieId) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(externalAPIService.getMovieVideoById(movieId), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/movie/genre/{genreId}/{pageNo}")
    public ResponseEntity<?> sortMoviesByGenre(@PathVariable int genreId, @PathVariable int pageNo) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<>(externalAPIService.sortMoviesByGenre(genreId, pageNo), HttpStatus.OK);
        }
        catch(Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
