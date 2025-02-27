package com.bej.movieservice.repository;

import com.bej.movieservice.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMovieRepository extends MongoRepository<User,String> {

}
