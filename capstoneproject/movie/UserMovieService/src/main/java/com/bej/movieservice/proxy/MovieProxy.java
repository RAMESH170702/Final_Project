package com.bej.movieservice.proxy;

import com.bej.movieservice.domain.Movie;
import com.bej.movieservice.domain.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="movie-service",url="localhost:8096")
public interface MovieProxy {
    @GetMapping("/movies/movie/recommendations/{movieId}")
    public ResponseEntity<List<Movie>> getMovieRecommendationsById (@PathVariable Long movieId);
}
