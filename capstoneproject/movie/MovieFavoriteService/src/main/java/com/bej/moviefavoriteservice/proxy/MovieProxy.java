package com.bej.moviefavoriteservice.proxy;

import com.bej.moviefavoriteservice.domain.MovieDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="movie-service",url="localhost:8096")
public interface MovieProxy {
    @GetMapping("/api/v4/movies/movie/{movieId}")
    public ResponseEntity<MovieDetails> getMovieById(@PathVariable Long movieId);
}
