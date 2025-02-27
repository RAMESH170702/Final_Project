package com.bej.movieservice.repository;

import com.bej.movieservice.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, Integer> {
}
