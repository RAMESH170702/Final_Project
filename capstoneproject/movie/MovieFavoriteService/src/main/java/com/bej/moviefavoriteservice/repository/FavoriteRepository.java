package com.bej.moviefavoriteservice.repository;

import com.bej.moviefavoriteservice.domain.Favorite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoriteRepository extends MongoRepository<Favorite, String> {

}
