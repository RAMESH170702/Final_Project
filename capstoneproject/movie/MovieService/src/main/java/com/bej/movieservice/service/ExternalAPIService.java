package com.bej.movieservice.service;

import com.bej.movieservice.domain.CastMember;
import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.MovieDetails;
import com.bej.movieservice.domain.MovieVideoResultDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExternalAPIService {
    List<Movie> fetchAllMovies(int page);
    void findGenre();
    MovieDetails getMovieById(Long movieId);
    List<Movie> getMovieByTitle(String title);
    List<Movie> getMovieRecommendationsById (Long movieId);
    List<CastMember> getMovieCast (Long movieId);
    List<MovieVideoResultDTO> getMovieVideoById(Long movieId);
    List<Movie> sortMoviesByGenre(int genreId, int pageNo);
}
