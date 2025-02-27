package com.bej.movieservice.proxy;

import com.bej.movieservice.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="movie-favorite-service",url="localhost:8095")
public interface FavoriteProxy {
    @PostMapping("/api/v3/favorites/save")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}
