package com.bej.movieservice.repository;

import com.bej.movieservice.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, Long> {
//    Movie findByMovieTitle(String title);

}
